package library.commands;

import library.models.Book;
import library.models.Genre;
import library.models.AccessLevel;
import library.repository.FileRepository;
import java.util.ArrayList;
import java.util.List;

public class BooksCommand implements Command {
    private FileRepository storage;

    public BooksCommand(FileRepository storage) {
        this.storage = storage;
    }

    @Override
    public String execute(String[] args) {
        if (storage.getLoggedUser() == null) {
            return "You must be logged in.";
        }

        if (args.length < 2) {
            return "Invalid command.";
        }

        String action = args[1].toLowerCase();

        if (action.equals("all") || action.equals("view")) {
            List<Book> books = storage.getAllBooks();
            if (books.isEmpty()) return "Library is empty.";
            StringBuilder result = new StringBuilder();
            for (Book b : books) {
                result.append(b.getTitle()).append(", ").append(b.getAuthor()).append(", ")
                        .append(b.getGenre()).append(", ").append(b.getId()).append("\n");
            }
            return result.toString().trim();
        }

        if (action.equals("info") && args.length > 2) {
            for (Book b : storage.getAllBooks()) {
                if (b.getId().equals(args[2])) {
                    return "Author: " + b.getAuthor() + "\nTitle: " + b.getTitle() + "\nGenre: " + b.getGenre() +
                            "\nDescription: " + b.getShortDescription() + "\nYear: " + b.getPublishedYear() +
                            "\nRating: " + b.getRating() + "\nID: " + b.getId();
                }
            }
            return "Book not found.";
        }

        if (action.equals("find") && args.length > 2) {
            String[] findParts = args[2].split(" ", 2);
            if (findParts.length < 2) return "Please provide search term.";
            String criterion = findParts[0].toLowerCase();
            String searchString = findParts[1].toLowerCase();
            StringBuilder result = new StringBuilder();
            for (Book b : storage.getAllBooks()) {
                boolean match = false;
                if (criterion.equals("title") && b.getTitle().toLowerCase().contains(searchString)) match = true;
                else if (criterion.equals("author") && b.getAuthor().toLowerCase().contains(searchString)) match = true;
                else if (criterion.equals("tag") && b.getKeywords().toString().toLowerCase().contains(searchString)) match = true;
                if (match) result.append(b.getTitle()).append(", ").append(b.getAuthor()).append(", ").append(b.getId()).append("\n");
            }
            return result.length() == 0 ? "No matches." : result.toString().trim();
        }

        if (action.equals("sort") && args.length > 2) {
            String[] sortParts = args[2].split(" ", 2);
            String option = sortParts[0].toLowerCase();
            boolean asc = sortParts.length < 2 || !sortParts[1].equalsIgnoreCase("desc");
            List<Book> sorted = new ArrayList<>(storage.getAllBooks());
            for (int i = 1; i < sorted.size(); i++) {
                Book key = sorted.get(i);
                int j = i - 1;
                while (j >= 0 && compareInternal(sorted.get(j), key, option, asc) > 0) {
                    sorted.set(j + 1, sorted.get(j));
                    j--;
                }
                sorted.set(j + 1, key);
            }
            StringBuilder res = new StringBuilder();
            for (Book b : sorted) res.append(b.getTitle()).append(", ").append(b.getAuthor()).append(", ").append(b.getId()).append("\n");
            return res.toString().trim();
        }

        if (action.equals("add")) {
            if (storage.getLoggedUserRole() == null || storage.getLoggedUserRole() != AccessLevel.ADMIN) {
                return "Access denied.";
            }
            if (args.length < 3) return "No data provided.";

            String[] d = args[2].split("\\|", -1);
            if (d.length < 8) return "Error: Incomplete data.";

            try {
                if (d[0].trim().isEmpty() || d[1].trim().isEmpty() || d[3].trim().isEmpty() ||
                        d[5].trim().isEmpty() || d[7].trim().isEmpty()) {
                    return "Error: Some fields are empty!";
                }

                Genre genre = Genre.valueOf(d[2].toUpperCase().trim());
                List<String> keyList = new ArrayList<>();
                for (String key : d[5].split(",")) {
                    String trimmedKey = key.trim();
                    if (!trimmedKey.isEmpty()) {
                        keyList.add(trimmedKey);
                    }
                }

                Book nb = new Book(d[0].trim(), d[1].trim(), genre, d[3].trim(),
                        Integer.parseInt(d[4].trim()), keyList,
                        Double.parseDouble(d[6].trim()), d[7].trim());
                storage.addBook(nb);
                return "Book added successfully.";
            } catch (NumberFormatException e) {
                return "Error: Year must be a number and rating must be a valid decimal!";
            } catch (IllegalArgumentException e) {
                return "Error: Invalid genre!";
            } catch (Exception e) {
                return "Error: Invalid data!";
            }
        }

        if (action.equals("remove") && args.length > 2) {
            if (storage.getLoggedUserRole() != AccessLevel.ADMIN) {
                return "Access denied.";
            }
            boolean removed = storage.getAllBooks().removeIf(b -> b.getId().equals(args[2]));
            return removed ? "Book removed." : "Book not found.";
        }

        return "Invalid command.";
    }

    private int compareInternal(Book b1, Book b2, String option, boolean asc) {
        int res = 0;
        if (option.equals("title")) res = b1.getTitle().compareToIgnoreCase(b2.getTitle());
        else if (option.equals("author")) res = b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
        else if (option.equals("year")) res = Integer.compare(b1.getPublishedYear(), b2.getPublishedYear());
        else if (option.equals("rating")) res = Double.compare(b1.getRating(), b2.getRating());
        return asc ? res : -res;
    }
}