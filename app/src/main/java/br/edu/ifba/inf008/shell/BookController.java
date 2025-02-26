package br.edu.ifba.inf008.shell;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.IBookController;

public class BookController implements IBookController {
    private HashMap<String, IBook> booksList = new HashMap<String, IBook>();

    public BookController() {
    }

    public boolean isValidBookData(String title, String ISBN, String author, String genre, String publicationYear) {
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

        addBook(new Book(title, ISBN, author, genre, publicationYear));

        return true;
    }

    public boolean isValidTitle(String title) {
        if ("".equals(title) || title == null) {
            return false;
        }
        return true;
    }

    public boolean isValidISBN(String ISBN) {
        boolean ISBNExist = getBooksMap().containsKey(ISBN);

        if ("".equals(ISBN) || ISBN == null || ISBNExist) {
            return false;
        }
        return true;
    }

    public boolean isValidAuthor(String author) {
        if ("".equals(author) || author == null) {
            return false;
        }
        return true;
    }

    public boolean isValidGenre(String genre) {
        if ("".equals(genre) || genre == null) {
            return false;
        }
        return true;
    }

    public boolean isValidPublicationYear(String publicationYear) {
        return true;
    }

    public void addBook(IBook newBook) {
        booksList.put(newBook.getISBN(), newBook);
    }

    public IBook getBook(String BookISBN) {
        return booksList.get(BookISBN);
    }

    public HashMap<String, IBook> getBooksMap() {
        return booksList;
    }

    public void loadBookMap(HashMap<String, IBook> books) {
        booksList = books;
    }

    public Collection<IBook> getMatchingPatternBooks(String searchPattern) {
        var matchPatternBooks = new ArrayList<IBook>();

        for (IBook book : booksList.values()) {
            if (book.getTitle().contains(searchPattern)) {
                matchPatternBooks.add(book);
            }
        }
        return matchPatternBooks;
    }

    public ArrayList<IBook> getRentedBooksList() {
        if (booksList.isEmpty() || booksList == null) {
            return null;
        }

        var rentedBooksList = new ArrayList<IBook>();

        for (IBook book : booksList.values()) {
            if (!book.isAvailable()) {
                rentedBooksList.add(book);
            }
        }
        return rentedBooksList.isEmpty() ? null : rentedBooksList;
    }

    public void test() {
        isValidBookData("1984",
                "9742579837598",
                "George Orwell",
                "Novel",
                "08/06/1949");
        isValidBookData("Animal Farm",
                "6476547575547",
                "George Orwell",
                "Fiction",
                "17/08/1945");
        isValidBookData("Lord of the flies",
                "346564654644566",
                "William Golding",
                "Novel",
                "17/09/1954");
        isValidBookData("Notes from the underground",
                "43764757576734",
                "Fyodor Dostoevsky",
                "Fiction",
                "01/04/1864");
        isValidBookData("Alice's adventures in Wonderland",
                "67575476575654",
                "Lewis Carrel",
                "Fantasy Fiction",
                "01/11/1865");
        isValidBookData("Brave New World",
                "9780060850524",
                "Aldous Huxley",
                "Dystopian",
                "01/01/1932");
        isValidBookData("The Catcher in the Rye",
                "9780316769488",
                "J.D. Salinger",
                "Fiction",
                "16/07/1951");
        isValidBookData("To Kill a Mockingbird",
                "9780061120084",
                "Harper Lee",
                "Fiction",
                "11/07/1960");
        isValidBookData("The Great Gatsby",
                "9780743273565",
                "F. Scott Fitzgerald",
                "Novel",
                "10/04/1925");
        isValidBookData("Moby-Dick",
                "9781503280786",
                "Herman Melville",
                "Adventure",
                "18/10/1851");
        isValidBookData("Crime and Punishment",
                "9780486415871",
                "Fyodor Dostoevsky",
                "Philosophical Fiction",
                "01/01/1866");
        isValidBookData("Pride and Prejudice",
                "9781503290563",
                "Jane Austen",
                "Romance",
                "28/01/1813");
        isValidBookData("Wuthering Heights",
                "9780141439556",
                "Emily Brontë",
                "Gothic Fiction",
                "01/12/1847");
        isValidBookData("The Brothers Karamazov",
                "9780374528379",
                "Fyodor Dostoevsky",
                "Philosophical Novel",
                "01/01/1880");
        isValidBookData("Frankenstein",
                "9780486282114",
                "Mary Shelley",
                "Horror",
                "01/01/1818");
        isValidBookData("The Odyssey",
                "9780140268867",
                "Homer",
                "Epic Poetry",
                "01/01/-700");
        isValidBookData("Don Quixote",
                "9780060934347",
                "Miguel de Cervantes",
                "Adventure",
                "16/01/1605");
        isValidBookData("One Hundred Years of Solitude",
                "9780060883287",
                "Gabriel García Márquez",
                "Magical Realism",
                "30/05/1967");
        isValidBookData("The Metamorphosis",
                "9780553213690",
                "Franz Kafka",
                "Absurdist Fiction",
                "01/10/1915");
        isValidBookData("The Divine Comedy",
                "9780140448955",
                "Dante Alighieri",
                "Epic Poetry",
                "01/01/1320");
    }
}
