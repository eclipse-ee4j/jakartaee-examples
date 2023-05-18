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
package jakarta.tutorial.dukesbookstore.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 * <p>Singleton bean that initializes the book database for the bookstore
 * example.</p>
 */
@Singleton
@Startup
public class ConfigBean {

    @EJB
    private BookRequestBean request;

    @PostConstruct
    public void createData() {
        request.createBook("201", "Duke", "",
                "My Early Years: Growing Up on *7",
                30.75, false, 2005, "What a cool book.", 20);
        request.createBook("202", "Jeeves", "",
                "Web Servers for Fun and Profit", 40.75, true,
                2010, "What a cool book.", 20);
        request.createBook("203", "Masterson", "Webster",
                "Web Components for Web Developers",
                27.75, false, 2010, "What a cool book.", 20);
        request.createBook("205", "Novation", "Kevin",
                "From Oak to Java: The Revolution of a Language",
                10.75, true, 2008, "What a cool book.", 20);
        request.createBook("206", "Thrilled", "Ben",
                "The Green Project: Programming for Consumer Devices",
                30.00, true, 2008, "What a cool book.", 20);
        request.createBook("207", "Coding", "Happy",
                "Java Intermediate Bytecodes", 30.95, true,
                2010, "What a cool book.", 20);

    }
}
