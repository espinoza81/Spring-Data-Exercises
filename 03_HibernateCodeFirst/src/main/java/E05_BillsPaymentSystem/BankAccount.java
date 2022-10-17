package E05_BillsPaymentSystem;

import javax.persistence.*;

@Entity
@Table(name = "_05_bank_account")
public class BankAccount extends BillingDetail{
    @Enumerated(EnumType.STRING)
    private final BillingType billingType;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "swift_code", nullable = false)
    private String swiftCode;

    public BankAccount() {
        this.billingType = BillingType.BANK_ACCOUNT;
    }

    public BankAccount(String bankName, String swiftCode) {
        this.bankName = bankName;
        this.swiftCode = swiftCode;
        this.billingType = BillingType.BANK_ACCOUNT;
    }

    public BillingType getBillingType() {
        return billingType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
