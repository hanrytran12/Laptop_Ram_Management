package Main;


import Controller.RAMManagementSystem;
import Model.RAMItem;
import Service.InputService;
import View.RAMItemView;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Huy
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        RAMItemView view = new RAMItemView();
        RAMManagementSystem listRAMItem = new RAMManagementSystem();
        boolean haveSelect = false;
        boolean isSave = false;
        do {
            view.displayMainMenu();
            int select;
            try {
                select = InputService.inputInteger("Choose your select: ");
            } catch (NumberFormatException e) {
                System.out.println("INVALID SELECT! PLEASE TRY AGAIN!");
                continue;
            }
            switch (select) {
                case 1: {
                    listRAMItem.addItem();
                    haveSelect = true;
                    break;
                }
                case 2: {
                    listRAMItem.searchItem();
                    break;
                }
                case 3:{
                    listRAMItem.updateItem();
                    haveSelect = true;
                    break;
                }
                case 4:{
                    listRAMItem.deleteItem();
                    haveSelect = true;
                    break;
                }
                case 5:{
                    listRAMItem.displayAllItem();
                    break;
                }
                case 6:{
                    listRAMItem.storeDataToFile();
                    isSave = true;
                    break;
                }
                case 7: {
                    if((isSave && haveSelect) || (!haveSelect)){
                        System.out.println("QUIT!");
                        return ;
                    }
                    else{
                        System.out.println("YOU NEED TO STORE TO DATA BEFORE EXIT!");
                        break;
                    }
                }
                default: {
                    System.out.println("WRONG SELECT! PLEASE TRY AGAIN!");
                }
            }
        } while (true);
    }
}
