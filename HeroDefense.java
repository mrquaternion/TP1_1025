public class HeroDefense extends Hero {
    
    // Constructeur
    public HeroDefense(int health, int maxHealth, int pointsAttaque) {
        super(health, maxHealth, (int)(pointsAttaque / 2));
    }

    // Autres méthodes
    @Override
    public int decreaseHealth(int enemyAttackPower) {
        this.health = health - (int)(enemyAttackPower / 2); // Le hero se prend 2 fois moins les points d'attaque de l'ennemi
        return health;
    }
}