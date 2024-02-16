public class HeroAttaque extends Hero {

    // Constructeur
    public HeroAttaque(int health, int attackPower) {
        super(health, attackPower * 2); // Les points d'attaque doublent
    }

    // Autres méthodes
    @Override
    public int decreaseHealth(int enemyAttackPower) {
        this.health = health - (enemyAttackPower * 2); // Le hero se prend 2 fois les points d'attaque de l'ennemi
        return health;
    }

    // -----------------Méthode statsUpdate()-----------------
    @Override
    public void statsUpdate() {
        this.maxHealth += maxHealthLevelUp;
        this.health = maxHealth;
        this.attackPower += (attackerPowerLevelUp * 2); // On a pas le même problème qu'avec HeroDefense
    }
}