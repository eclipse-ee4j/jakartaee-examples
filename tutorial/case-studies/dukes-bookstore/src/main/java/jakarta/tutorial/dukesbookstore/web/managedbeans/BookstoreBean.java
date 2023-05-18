/*
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER
 * RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package jakarta.tutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.FacesException;
import jakarta.inject.Named;
import jakarta.tutorial.dukesbookstore.ejb.BookRequestBean;
import jakarta.tutorial.dukesbookstore.entity.Book;
import jakarta.tutorial.dukesbookstore.exception.BookNotFoundException;
import jakarta.tutorial.dukesbookstore.exception.BooksNotFoundException;

/**
 * <p>Backing bean for the <code>/bookstore.xhtml</code> page.</p>
 */
@Named("store")
@SessionScoped
public class BookstoreBean extends AbstractBean implements Serializable {

    private static final Logger logger =
            Logger.getLogger("dukesbookstore.web.managedBeans.BookStoreBean");
    private static final long serialVersionUID = 7829793160074383708L;
    private Book featured = null;
    protected String title;
    @EJB
    BookRequestBean bookRequestBean;

    /**
     * @return the <code>Book</code> for the featured book
     */
    public Book getFeatured() {
        int featuredBookPos = 4; // "The Green Project"
        if (featured == null) {
            try {
                featured = (Book) bookRequestBean.getBooks().get(featuredBookPos);
            } catch (BooksNotFoundException e) {
                // Real apps would deal with error conditions better than this
                throw new FacesException("Could not get books: " + e);
            }
        }

        return (featured);
    }

    /**
     * <p>Add the featured item to our shopping cart.</p>
     * @return the navigation page
     */
    public String add() {
        Book book = getFeatured();
        cart.add(book.getBookId(), book);
        message(null, "ConfirmAdd", new Object[]{book.getTitle()});

        return ("bookcatalog");
    }

    public String addSelected() {
        logger.log(Level.INFO, "Entering BookstoreBean.addSelected");
        try {
            String bookId = (String) context().getExternalContext().
                    getSessionMap().get("bookId");
            Book book = bookRequestBean.getBook(bookId);
            cart.add(bookId, book);
            message(null, "ConfirmAdd", new Object[]{book.getTitle()});
        } catch (BookNotFoundException e) {
            throw new FacesException("Could not get book: " + e);
        }
        return ("bookcatalog");
    }

    /**
     * <p>Show the details page for the featured book.</p>
     * @return the navigation page
     */
    public String details() {
        context().getExternalContext().getSessionMap().put(
                "selected",
                getFeatured());

        return ("bookdetails");
    }

    public String selectedDetails() {
        logger.log(Level.INFO, "Entering BookstoreBean.selectedDetails");
        try {
            String bookId = (String) context().getExternalContext().getSessionMap().get("bookId");
            Book book = bookRequestBean.getBook(bookId);
            context().getExternalContext().getSessionMap().put("selected", book);
        } catch (BookNotFoundException e) {
            throw new FacesException("Could not get book: " + e);
        }
        return ("bookdetails");
    }

    public String getSelectedTitle() {
        logger.log(Level.INFO, "Entering BookstoreBean.getSelectedTitle");
        try {
            String bookId = (String) context().getExternalContext().getSessionMap().get("bookId");
            Book book = bookRequestBean.getBook(bookId);
            title = book.getTitle();
        } catch (BookNotFoundException e) {
            throw new FacesException("Could not get book title: " + e);
        }
        return title;
    }

    public List<Book> getBooks() {
        try {
            return bookRequestBean.getBooks();
        } catch (BooksNotFoundException e) {
            throw new FacesException("Exception: " + e);
        }
    }
}
