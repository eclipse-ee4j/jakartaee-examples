/*
 * Permission to use, copy, modify, and/or distribute this software for any 
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package jakartaee.examples.jpa.singleTableInheritance;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * A simple EJB that persist shapes in database
 * 
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Stateless
public class ShapeEjb {

    /**
     * Stores the persistence context.
     */        
    @PersistenceContext(unitName = "inheritancePU")
    private EntityManager em;
    
    /**
     * Persist a circle and a rectangle in database     
     */
    public void persist(){                       

        Circle circle = new Circle(10.0F);
        circle.setName("A Circle");
        Rectangle rectangle = new Rectangle(20,30);        
        rectangle.setName("A Rectangle");
        
        em.persist(circle);
        em.persist(rectangle);
        
    }
    
    public List<Circle> findAllCircles(){
        return em.createQuery("FROM Circle c").getResultList();
    }

    public List<Rectangle> findAllRectangles() {
        return em.createQuery("FROM Rectangle r").getResultList();
    }
    
}
