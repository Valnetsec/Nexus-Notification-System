public class PushNotification extends Notification {
    private String deviceId;

    public PushNotification(String deviceId, String message) {
        super(
            "Nexus System",
            deviceId,
            "Push Notification",
            message
        );

        this.deviceId = deviceId;
    }

    @Override
    public void send() {
        System.out.println("\n=== PUSH NOTIFICATION ===");
        System.out.println("Perangkat : " + deviceId);
        System.out.println("Pesan     : " + getMessage());
        System.out.println("Status    : Push notification berhasil dikirim.");
    }

    @Override
    public void display() {
        System.out.println("\n=== ISI PUSH NOTIFICATION ===");
        System.out.println("Perangkat : " + deviceId);
        System.out.println("Pesan     : " + getMessage());
    }
}