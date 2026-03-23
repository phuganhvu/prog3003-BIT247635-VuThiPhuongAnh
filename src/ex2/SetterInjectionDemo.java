package ex2;

interface MessageService {
    void sendMessage(String message);
}

class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Email: " + message);
    }
}

class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("SMS: " + message);
    }
}

class Notification {
    private MessageService messageService;

    // Setter injection
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void processMessages(String msg) {
        if (this.messageService != null) {
            this.messageService.sendMessage(msg);
        } else {
            System.out.println("No message service set!");
        }
    }
}

public class SetterInjectionDemo {
    public static void main(String[] args) {
        Notification notification = new Notification();
        
        // Inject EmailService
        MessageService emailService = new EmailService();
        notification.setMessageService(emailService);
        notification.processMessages("Xin chào qua Email!");

        // Inject SMSService
        MessageService smsService = new SMSService();
        notification.setMessageService(smsService);
        notification.processMessages("Xin chào qua SMS!");
    }
}
