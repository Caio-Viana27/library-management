package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import java.util.HashMap;

public class Book implements IPlugin {
    private String title;
    private String ISBN;
    private String author;
    private String genre;
    private String publicationYear;
    private boolean isAvailable = true;

    private static HashMap<String, Book> listOfBooks = new HashMap<String, Book>();

    public boolean init() {
        createTestBooks();
        // load the books to the static atribute
        return true;
    }

    public Book() {
    }

    private Book(String title, String ISBN, String author, String genre, String publicationYear) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    public static boolean createBook(String title, String ISBN, String author, String genre, String publicationYear) {
        if (!isValidTitle(title)) {
            return false;
        }
        if (!isValidISBN(ISBN)) {
            return false;
        }
        if (!isValidAuthor(author)) {
            return false;
        }
        if (!isValidPublicationYear(publicationYear)) {
            return false;
        }

        listOfBooks.put(ISBN, new Book(title, ISBN, author, genre, publicationYear));

        return true;
    }

    public static boolean isValidTitle(String title) {
        if ("".equals(title) || title == null) {
            return false;
        }
        return true;
    }

    public static boolean isValidISBN(String ISBN) {
        if ("".equals(ISBN) || ISBN == null || listOfBooks.containsKey(ISBN)) {
            return false;
        }
        return true;
    }

    public static boolean isValidAuthor(String author) {
        if ("".equals(author) || author == null) {
            return false;
        }
        return true;
    }

    public static boolean isValidGenre(String genre) {
        if ("".equals(genre) || genre == null) {
            return false;
        }
        return true;
    }

    public static boolean isValidPublicationYear(String publicationYear) {
        return true;
    }

    public static HashMap<String, Book> getListOfBooks() {
        return listOfBooks;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    private void createTestBooks() {
        Book.createBook("1984",
                "9742579837598",
                "George Orwell",
                "Novel",
                "08/06/1949");
        Book.createBook("Animal Farm",
                "6476547575547",
                "George Orwell",
                "Fiction",
                "17/08/1945");
        Book.createBook("Lord of the flies",
                "346564654644566",
                "William Golding",
                "Novel",
                "17/09/1954");
        Book.createBook("Notes from the underground",
                "43764757576734",
                "Fyodor Dostoevsky",
                "Fiction",
                "01/04/1864");
        Book.createBook("Alice's adventures in Wonderland",
                "67575476575654",
                "Lewis Carrel",
                "Fantasy Fiction",
                "01/11/1865");
        Book.createBook("Brave New World",
                "9780060850524",
                "Aldous Huxley",
                "Dystopian",
                "01/01/1932");
        Book.createBook("The Catcher in the Rye",
                "9780316769488",
                "J.D. Salinger",
                "Fiction",
                "16/07/1951");
        Book.createBook("To Kill a Mockingbird",
                "9780061120084",
                "Harper Lee",
                "Fiction",
                "11/07/1960");
        Book.createBook("The Great Gatsby",
                "9780743273565",
                "F. Scott Fitzgerald",
                "Novel",
                "10/04/1925");
        Book.createBook("Moby-Dick",
                "9781503280786",
                "Herman Melville",
                "Adventure",
                "18/10/1851");
        Book.createBook("Crime and Punishment",
                "9780486415871",
                "Fyodor Dostoevsky",
                "Philosophical Fiction",
                "01/01/1866");
        Book.createBook("Pride and Prejudice",
                "9781503290563",
                "Jane Austen",
                "Romance",
                "28/01/1813");
        Book.createBook("Wuthering Heights",
                "9780141439556",
                "Emily Brontë",
                "Gothic Fiction",
                "01/12/1847");
        Book.createBook("The Brothers Karamazov",
                "9780374528379",
                "Fyodor Dostoevsky",
                "Philosophical Novel",
                "01/01/1880");
        Book.createBook("Frankenstein",
                "9780486282114",
                "Mary Shelley",
                "Horror",
                "01/01/1818");
        Book.createBook("The Odyssey",
                "9780140268867",
                "Homer",
                "Epic Poetry",
                "01/01/-700");
        Book.createBook("Don Quixote",
                "9780060934347",
                "Miguel de Cervantes",
                "Adventure",
                "16/01/1605");
        Book.createBook("One Hundred Years of Solitude",
                "9780060883287",
                "Gabriel García Márquez",
                "Magical Realism",
                "30/05/1967");
        Book.createBook("The Metamorphosis",
                "9780553213690",
                "Franz Kafka",
                "Absurdist Fiction",
                "01/10/1915");
        Book.createBook("The Divine Comedy",
                "9780140448955",
                "Dante Alighieri",
                "Epic Poetry",
                "01/01/1320");
    }
}
