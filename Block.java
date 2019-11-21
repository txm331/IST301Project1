package ist.psu.edu;

import java.util.ArrayList;
import java.util.Date;
import java.security.MessageDigest;
import java.util.Scanner;


enum PaymentType {cash, credit, debit}


public class Block {

    //public data variables
    public String hash;
    public String previousHash;
    //private data variables
    private String description;
    private long timeStamp;
    private int transaction;
    private PaymentType paymentType;

    //variable Constructor method for Block class
    public Block(String prevHash){
        this.previousHash = prevHash;
        this.hash = calculateHash();
        this.timeStamp = new Date().getTime();

    }
    //get-set values for variables
    public void setPaymentType(PaymentType paymentType){this.paymentType = paymentType;}
    public PaymentType getPaymentType(){return paymentType;}

    public void setTransaction(int transaction) { this.transaction = transaction; }
    public int getTransaction(){ return transaction; }

    public void setDescription(String description) {this.description = description;}
    public String getDescription() { return description; }

    //Method that prints out the information
    public static void listTransactions(ArrayList<Block> tList) {
        for (Block transaction : tList) {
            System.out.println("Current Hash: " + transaction.calculateHash());
            System.out.println("Previous Hash: " + transaction.hash);
            System.out.println("Transaction complete: " + transaction.getTransaction() + " dollar(s) has been added to your account");
            System.out.println("Reason for Transaction: " + transaction.getDescription());
            System.out.println("PaymentType: " + transaction.getPaymentType());
            System.out.println("Time Stamp: " + transaction.timeStamp);


        }
    }


    public static String useSha256(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            StringBuffer hexString = new StringBuffer();
            for(int i = 0; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String calculateHash(){
        String calcHash = useSha256(previousHash + Long.toString(timeStamp) + getDescription());

        return calcHash;
    }



}
