package xyz.zsuatem.appstore.people;

public class Client extends Human {

    private ClientType clientType;

    public Client(String fullName) {
        super(fullName);
        clientType = ClientType.getRandomClientType();
    }

    public ClientType getClientType() {
        return clientType;
    }
}
