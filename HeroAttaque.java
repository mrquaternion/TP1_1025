public class HeroAttaque extends Hero {

    // Constructeur
    public HeroAttaque(int health, int maxHealth, int pointsAttaque) {
        super(health, maxHealth, pointsAttaque * 2); // Les points d'attaque doublent
    }

    // Autres méthodes
    @Override
    public int decreaseHealth(int enemyAttackPower) {
        this.health = health - (enemyAttackPower * 2); // Le hero se prend 2 fois les points d'attaque de l'ennemi
        return health;
    }
}