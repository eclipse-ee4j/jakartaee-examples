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
package jakarta.tutorial.batch.phonebilling.items;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

/* This class is a Java Persistence API entity that
 * represents a phone bill in the batch application.
 */
@Entity
public class PhoneBill implements Serializable {

    @Id
    private String phoneNumber;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @OrderBy("datetime ASC")
    private List<CallRecord> calls;
    private BigDecimal amountBase;
    private BigDecimal taxRate;
    private BigDecimal tax;
    private BigDecimal amountTotal;
    private static final long serialVersionUID = -7617311744533479326L;

    public PhoneBill() {
    }
    
    public PhoneBill(String number) {
        this.phoneNumber = number;
        calls = new ArrayList<>();
    }
    
    public void addCall(CallRecord call) {
        calls.add(call);
    }
    
    public void calculate(BigDecimal taxRate) {
        /* Compute the total amount and tax */
        this.taxRate = taxRate;
        amountBase = new BigDecimal(0).setScale(2);
        for (CallRecord call : calls) {
            amountBase = amountBase.add(call.getPrice());
        }
        tax = amountBase.multiply(taxRate).setScale(2, RoundingMode.HALF_EVEN);
        amountTotal = amountBase.add(tax);
    }
    
    public String getPhoneNumber() { return phoneNumber; }
    public List<CallRecord> getCalls() { return calls; }
    public BigDecimal getAmountBase() { return amountBase; }
    public BigDecimal getTax() { return tax; }
    public BigDecimal getTaxRate() { return taxRate; }
    public BigDecimal getAmountTotal() { return amountTotal; }
}
