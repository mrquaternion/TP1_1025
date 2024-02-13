public class HeroDefense extends Hero {
    private float tempAttackPower;

    // Constructeur
    public HeroDefense(int health, int maxHealth, int attackPower) {
        super(health, maxHealth, (int)(attackPower / 2));
        this.tempAttackPower = attackPower;
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
        this.attackPower = (int)(tempAttackPower + (attackerPowerLevelUp / 2)); // On force les vrais points d'attaque du hero à être divisé par 2
        this.tempAttackPower = tempAttackPower + (attackerPowerLevelUp / 2); // Cas spéciaux à chaque 2x l'amélioration --> 1ere (int)(5+2.5) = 7, 2e (int)(7+2.5) = 9 mais on veut 
                                                                            // 10 alors tempAttackPower garde la valeur décimale pour le niveau où la vraie somme est entière 
    }
}