public class HeroAttaque extends Hero {

    // Constructeur
    public HeroAttaque(int health, int maxHealth, int pointsAttaque) {
        super(health, maxHealth, pointsAttaque * 2); // Les points d'attaque doublent
    }
}