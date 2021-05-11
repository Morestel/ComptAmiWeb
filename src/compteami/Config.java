package compteami;

import java.awt.Dimension;

public interface Config {
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    public final int HAUTEUR_FENETRE = (int)dimension.getHeight();
    public final int LARGEUR_FENETRE = (int)dimension.getWidth();
    public final int POS_X = 0;
    public final int POS_Y = 0;


    public final String MAIL = "comptami456@gmail.com";
    public final String MOT_DE_PASSE = "BaobabMVP";
}
