import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Account account1 = new Account(
            "User 1",
            "user1@nexus.com"
        );

        Account account2 = new Account(
            "User 2",
            "user2@nexus.com"
        );

        Account[] accounts = {account1, account2};

        boolean running = true;

        while (running) {
            System.out.println("\n===== NEXUS MAIL =====");
            System.out.println("1. Login sebagai " + account1.getEmail());
            System.out.println("2. Login sebagai " + account2.getEmail());
            System.out.println("0. Keluar");
            System.out.print("Pilih akun: ");

            String choice = input.nextLine().trim();

            Account current;
            Account other;

            if (choice.equals("1")) {
                current = account1;
                other = account2;
            } else if (choice.equals("2")) {
                current = account2;
                other = account1;
            } else if (choice.equals("0")) {
                running = false;
                continue;
            } else {
                System.out.println("Pilihan tidak valid.");
                continue;
            }

            boolean loggedIn = true;

            while (loggedIn && running) {
                System.out.println("\n===== MENU EMAIL =====");
                System.out.println("Login: " + current.getEmail());
                System.out.println("1. Tulis email");
                System.out.println("2. Buka inbox");
                System.out.println("3. Ganti akun");
                System.out.println("0. Keluar aplikasi");
                System.out.print("Pilih menu: ");

                String menu = input.nextLine().trim();

                switch (menu) {
                    case "1": {
                        System.out.println(
                            "Penerima: " + other.getEmail()
                        );

                        System.out.print("Subjek: ");
                        String subject = input.nextLine();

                        System.out.print("Tulis pesan: ");
                        String message = input.nextLine();

                        if (subject.trim().isEmpty()
                                || message.trim().isEmpty()) {
                            System.out.println(
                                "Subjek dan pesan tidak boleh kosong."
                            );
                        } else {
                            current.sendEmail(
                                other,
                                subject,
                                message
                            );
                        }

                        break;
                    }

                    case "2": {
                        current.showInbox();

                        if (current.getInboxSize() == 0) {
                            break;
                        }

                        System.out.print(
                            "Pilih nomor email untuk dibuka "
                            + "(0 untuk kembali): "
                        );

                        String numberText = input.nextLine().trim();
                        int number;

                        try {
                            number = Integer.parseInt(numberText);
                        } catch (NumberFormatException e) {
                            System.out.println(
                                "Masukkan nomor yang valid."
                            );
                            break;
                        }

                        if (number == 0) {
                            break;
                        }

                        if (number < 1
                                || number > current.getInboxSize()) {
                            System.out.println(
                                "Nomor email tidak tersedia."
                            );
                            break;
                        }

                        Notification selected =
                            current.getInboxMessage(number - 1);

                        selected.display();

                        System.out.print(
                            "Balas email ini? (y/n): "
                        );

                        String answer = input.nextLine()
                            .trim();

                        if (answer.equalsIgnoreCase("y")) {
                            Account sender = findAccount(
                                accounts,
                                selected.getSenderEmail()
                            );

                            if (sender == null) {
                                System.out.println(
                                    "Akun pengirim tidak ditemukan."
                                );
                                break;
                            }

                            String replySubject =
                                selected.getSubject();

                            if (!replySubject
                                    .toLowerCase()
                                    .startsWith("re:")) {
                                replySubject =
                                    "Re: " + replySubject;
                            }

                            System.out.print(
                                "Tulis pesan balasan: "
                            );

                            String reply = input.nextLine();

                            if (reply.trim().isEmpty()) {
                                System.out.println(
                                    "Pesan balasan tidak boleh kosong."
                                );
                            } else {
                                current.sendEmail(
                                    sender,
                                    replySubject,
                                    reply
                                );
                            }
                        }

                        break;
                    }

                    case "3":
                        loggedIn = false;
                        break;

                    case "0":
                        running = false;
                        loggedIn = false;
                        break;

                    default:
                        System.out.println(
                            "Pilihan tidak valid."
                        );
                }
            }
        }

        input.close();
        System.out.println("Nexus Mail ditutup.");
    }

    private static Account findAccount(
            Account[] accounts, String email) {

        for (Account account : accounts) {
            if (account.getEmail().equalsIgnoreCase(email)) {
                return account;
            }
        }

        return null;
    }
}