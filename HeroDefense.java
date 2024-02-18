public class HeroDefense extends Hero {

    // Constructeur
    public HeroDefense(int health, int attackPower) {
        super(health, (int)(attackPower / 2));
    }

    // Autres méthodes
    @Override
    public int decreaseHealth(int enemyAttackPower) {
        this.health = health - (int)(enemyAttackPower / 2); // Le hero se prend 2 fois moins les points d'attaque de l'ennemi
        return health;
    }

    // -----------------Méthode statsUpdate()-----------------
    @Override
    public void statsUpdate() {
        this.maxHealth += maxHealthLevelUp;
        this.health = maxHealth;
        this.attackPower += (int)(attackerPowerLevelUp / 2);  
    }
}
