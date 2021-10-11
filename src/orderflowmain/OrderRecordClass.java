/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

/**
 *
 * @author ashtonhess
 */
public class OrderRecordClass {
    
    //ContactTable
    public Integer orderNumber;
    public String contactEmail;
    public String billingName;
    public String shippingName;
    public String billingPhone;
    public String billingStreet;
    public String billingAdd1;
    public String billingAdd2;
    public String billingCity;
    public String billingState;
    public String billingZip;
    public String billingCountry;
    
    //StatusTable
    public String createdAt;
    public boolean financialStatusPaid;
    public boolean fufillmentStatus;
    public String orderNotes;
    
    //OrderTable
    public String discountCode;
    public double discountAmount;
    public String itemName;
    public double itemPrice;
    public Integer itemQuantity;
    public String itemSKU;
    public String orderNoteAttributes;
    
    public OrderRecordClass(){
        
    }
    
    //Record Constructor
    public OrderRecordClass(Integer orderNumber, String contactEmail, String billingName,
            String shippingName, String billingPhone, String billingStreet, String billingAdd1,
            String billingAdd2, String billingCity, String billingState, String billingZip,
            String billingCountry, String createdAt, boolean financialStatusPaid,
            boolean fufillmentStatus, String orderNotes, String discountCode, double discountAmount,
            String itemName, double itemPrice, Integer itemQuantity, String itemSKU, String orderNoteAttributes){
        
        this.orderNumber = orderNumber;
        this.contactEmail = contactEmail;
        this.billingName = billingName;
        this.shippingName = shippingName;
        this.billingPhone = billingPhone;
        this.billingStreet = billingStreet;
        this.billingAdd1 = billingAdd1;
        this.billingAdd2 = billingAdd2;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingZip = billingZip;
        this.billingCountry = billingCountry;
        this.createdAt = createdAt;
        this.financialStatusPaid = financialStatusPaid;
        this.fufillmentStatus = fufillmentStatus;
        this.orderNotes = orderNotes;
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemSKU = itemSKU;
        this.orderNoteAttributes = orderNoteAttributes;
        
    }
    
    public void printOrderRecordClass(){
        System.out.println("\n-------------------------------------------------\n"
                        +"Order Number: "+this.orderNumber
                        +"\nContact Email: "+this.contactEmail
                        +"\nFinancial Status Paid: "+this.financialStatusPaid
                        +"\nFufillment Status: "+this.fufillmentStatus
                        +"\nDiscount Code: "+this.discountCode
                        +"\nDiscount Amount: "+this.discountAmount
                        +"\nCreated At: "+this.createdAt
                        +"\nItem Quantity: "+this.itemQuantity
                        +"\nItem Name: "+this.itemName
                        +"\nItem Price: "+this.itemPrice
                        +"\nItem SKU: "+this.itemSKU
                        +"\nBilling Name: "+this.billingName
                        +"\nBilling Street: "+this.billingStreet
                        +"\nBilling Address1: "+this.billingAdd1
                        +"\nBilling Address2: "+this.billingAdd2
                        +"\nBilling City: "+this.billingCity
                        +"\nBilling Zip: "+this.billingZip
                        +"\nBilling State: "+this.billingState
                        +"\nBilling Country: "+ this.billingCountry
                        +"\nBilling Phone: "+this.billingPhone
                        +"\nShipping Name: "+this.shippingName
                        +"\nOrder Notes: "+this.orderNotes
                        +"\nOrder Note Attributes: "+this.orderNoteAttributes
                        +"\n-------------------------------------------------\n"
        
        );
    }
    
    
}
