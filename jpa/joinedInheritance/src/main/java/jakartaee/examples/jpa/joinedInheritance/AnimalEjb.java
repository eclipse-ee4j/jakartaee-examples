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

package jakartaee.examples.jpa.joinedInheritance;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * A simple EJB that persist animal data in database
 * 
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Stateless
public class AnimalEjb {

    /**
     * Stores the persistence context.
     */        
    @PersistenceContext(unitName = "inheritancePU")
    private EntityManager em;
    
    /**
     * Persist information of a cat and a dog in database     
     */
    public void persist(){                       

        Cat cat = new Cat(10);
        cat.setName("A Cat");
        Dog dog = new Dog(20);        
        dog.setName("A Dog");
        
        em.persist(cat);
        em.persist(dog);
        
    }
    
    public List<Cat> findAllCats(){
        return em.createQuery("FROM Cat c").getResultList();
    }

    public List<Dog> findAllDogs() {
        return em.createQuery("FROM Dog r").getResultList();
    }
    
}
