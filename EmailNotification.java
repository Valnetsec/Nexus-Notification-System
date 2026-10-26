public class EmailNotification extends Notification {

    public EmailNotification(String senderEmail, String recipientEmail,
                             String subject, String message) {
        super(senderEmail, recipientEmail, subject, message);
    }

    @Override
    public void send() {
        System.out.println("\nEmail berhasil dikirim!");
        System.out.println("Dari   : " + getSenderEmail());
        System.out.println("Kepada : " + getRecipientEmail());
        System.out.println("Subjek : " + getSubject());
    }

    @Override
    public void display() {
        System.out.println("\n========== ISI EMAIL ==========");
        System.out.println("Dari   : " + getSenderEmail());
        System.out.println("Kepada : " + getRecipientEmail());
        System.out.println("Subjek : " + getSubject());
        System.out.println("Pesan  : " + getMessage());
        System.out.println("===============================");
    }
}