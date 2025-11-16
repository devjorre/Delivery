

import Model.CardapioModel;
import View.MenuPrincipalView;

import javax.swing.SwingUtilities;

public class MainFront {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipalView menuPrincipalView = new MenuPrincipalView();
            menuPrincipalView.setVisible(true);
        });
    }

}
