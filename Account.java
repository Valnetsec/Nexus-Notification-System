import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private String email;
    private List<Notification> inbox;

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
        this.inbox = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void sendEmail(Account receiver, String subject, String message) {
        Notification emailMessage = new EmailNotification(
            this.email,
            receiver.getEmail(),
            subject,
            message
        );

        receiver.receive(emailMessage);
        emailMessage.send();
    }

    private void receive(Notification notification) {
        inbox.add(notification);
    }

    public int getInboxSize() {
        return inbox.size();
    }

    public Notification getInboxMessage(int index) {
        if (index < 0 || index >= inbox.size()) {
            return null;
        }

        return inbox.get(index);
    }

    public void showInbox() {
        System.out.println("\n========== INBOX ==========");
        System.out.println("Akun: " + email);

        if (inbox.isEmpty()) {
            System.out.println("Inbox masih kosong.");
            return;
        }

        for (int i = 0; i < inbox.size(); i++) {
            Notification notification = inbox.get(i);

            System.out.println((i + 1) + ". Dari: "
                + notification.getSenderEmail());

            System.out.println("   Subjek: "
                + notification.getSubject());
        }

        System.out.println("===========================");
    }
}