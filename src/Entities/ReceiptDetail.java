package Entities;

public class ReceiptDetail {
    private String id;
    private String receiptId;
    private String productId;
    private int amount;
    private int warrantyTime;
    private int total;

    public ReceiptDetail() {
    }

    public ReceiptDetail(String receiptId, String productId, int amount, int warrantyTime, int total) {
        this.receiptId = receiptId;
        this.productId = productId;
        this.amount = amount;
        this.warrantyTime = warrantyTime;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getWarrantyTime() {
        return warrantyTime;
    }

    public void setWarrantyTime(int warrantyTime) {
        this.warrantyTime = warrantyTime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
