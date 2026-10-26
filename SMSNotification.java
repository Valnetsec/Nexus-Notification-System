public class SMSNotification extends Notification {
    private int phoneNumber;

    public SMSNotification(int phoneNumber, String message) {
        super(
            "Nexus System",
            String.valueOf(phoneNumber),
            "SMS Notification",
            message
        );

        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send() {
        System.out.println("\n=== SMS NOTIFICATION ===");
        System.out.println("Tujuan : " + phoneNumber);
        System.out.println("Pesan  : " + getMessage());
        System.out.println("Status : SMS berhasil dikirim.");
    }

    @Override
    public void display() {
        System.out.println("\n=== ISI SMS ===");
        System.out.println("Nomor : " + phoneNumber);
        System.out.println("Pesan : " + getMessage());
    }
}