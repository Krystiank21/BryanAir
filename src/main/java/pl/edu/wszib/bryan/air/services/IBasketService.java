package pl.edu.wszib.bryan.air.services;

public interface IBasketService {
    double calculateTotal();
    void addFlightByIdToBasket(int id);
    void removeFlightByIdFromBasket(int id);
    boolean checkIfExist(int id);
}
