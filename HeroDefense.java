public class HeroDefense extends Hero {

    //--------------------// Constructeur //--------------------// 
    public HeroDefense(int health, int attackPower) {
        super(health, (int)(attackPower / 2)); // Les points d'attaque diminue par un facteur de 2
    }

    //--------------------// Autres méthodes //--------------------// 
    // Méthode decreaseHealth
    @Override
    public int decreaseHealth(int enemyAttackPower) {
        this.health -= (int)(enemyAttackPower / 2); // Le héro se prend 2 fois moins les points d'attaque de l'ennemi
        return health;
    }

    // Méthode statsUpdate
    @Override
    public void statsUpdate() {
        this.maxHealth += maxHealthLevelUp;
        this.health = maxHealth;
        this.attackPower += (int)(attackerPowerLevelUp / 2);  // On divise par deux les points d'attaque
    }

    // Méthode training
    @Override
    public void training(int attackTrainingBonus) { this.attackPower += (int)(attackTrainingBonus / 2); }
}
