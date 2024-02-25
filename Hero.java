import java.lang.Math;

public abstract class Hero {
    static int numberOfEnemiesDefeated = 0;

    //--------------------// Attributs privés //--------------------// 
    private int level = 1;
    private int experience = 0;

    //--------------------// Attributs protégés //--------------------// 
    protected int health;
    protected int maxHealth;
    protected int attackPower;

    //--------------------// Constantes disponibles dans HeroAttaque, HeroDefense et HeroEquilibre //--------------------//
    protected final int maxHealthLevelUp = 12;
    protected final int attackerPowerLevelUp = 6;
    
    //--------------------// Constructeur //--------------------//
    public Hero(int health, int attackPower) {
        setHealth(health);
        this.maxHealth = health;
        setAttackPower(attackPower);
    }

    //--------------------// Getters //--------------------//
    public int getHealth() { return this.health; }

    /* public int getMaxHealth() { return this.maxHealth; }

    public int getAttackPower() { return this.attackPower; } */

    public int getLevel() { return this.level; }

    // public int getExperience() { return this.experience; }

    //--------------------// Setters //--------------------//
    // private void setLevel(int level) { this.level = level; }

    // private void setExperience(int experience) { this.experience = experience; }

    private void setHealth(int health) {
        if (health > 0) {
            this.health = health;
        } else {
            throw new IllegalArgumentException("Les points de vie du héro doivent être supérieur à 0.");
        }
    }

    private void setAttackPower(int attackPower) {
        if (attackPower >= 0) { // Si le héro ne s'entraine pas dès le début, il meurt dès le premier tour, sinon il peut survivre
            this.attackPower = attackPower;
        } else {
            throw new IllegalArgumentException("Les points d'attaque du héro doivent être supérieur à 0.");
        }
    }

    //--------------------// Autres méthodes //--------------------//
    // Méthode levelUp
    public void levelUp() {
        if ((experience >= experiencePoints()) && level < 99) { // On regarde si le héro peut level up et s'il ne dépasse pas le niveau 99
            this.experience = 0; // On remet les points d'exp du héro à 0
            this.level += 1; // On augmente le niveau du héro
            this.statsUpdate(); // On augmente les stats du héro
        }
    }

    // Méthode statsUpdate
    public abstract void statsUpdate(); // Doit absolument être implémenté dans les sousclasses

    // Méthode decreaseHealth
    public abstract int decreaseHealth(int damageTaken); // Doit absolument être implémenté dans les sousclasses

    // Méthode experiencePoints
    private int experiencePoints() {
        int required_exp = (int)((50 + (level + 1) * 20 * Math.pow(1.1, level + 1)) + 0.99999); // Pour prendre l'entier supérieur, on ajoute .99999 (en fait jamais 1 autrement
                                                                                                    // s'il a 98 points d'exp, il levelUp alors qu'il faut pas
        return required_exp;
    }

    //--------------------// Méthodes d'action du héro //--------------------//
    // Méthode figthing
    public Boolean fighting(Enemy enemy) {
        while (this.getHealth() > 0 && enemy.getHealth() > 0) {
            // Le héro attaque en premier
            enemy.decreaseHealth(attackPower); 
            if (enemy.getHealth() <= 0) { // Vérifie si l'ennemi a toujours des points de vie
                break; // L'ennemi est mort
            }

            // L'ennemi attaque
            this.decreaseHealth(enemy.getAttackPower()); 
            if (this.getHealth() <= 0) { // Vérifie si le héro a toujours des points de vie
                break; // Le héro est mort
            }
        }

        // On regarde si le héro est mort après le combat
        if (this.getHealth() <= 0) { 
            return false; 
        } else {
            this.experience += enemy.getExperience(); // Le héro gagne de l'expérience
            Hero.numberOfEnemiesDefeated++; // On augmente le nombre d'ennemis battus
            return true;
        }
    }

    // Méthode resting
    public void resting() { this.health = maxHealth; } // Remet les points de vie du héro au maximum

    // Méthode healing
    public void healing(int healingPoint) {
        int newHealth = health + healingPoint;
        if (newHealth >= maxHealth) { // Vérifie que le soin ne depasse pas la limite maxHealth
            this.health = maxHealth;
        } else {
            this.health = newHealth;
        }
    }

    // Méthode training
    public void training(int attackTrainingBonus) { this.attackPower += attackTrainingBonus; } // Augmente les points d'attaque du Hero
}
