import java.lang.Math;

public class Hero {
    // Attributs privés
    private int health, maxHealth;
    private int level = 1;
    private int experience = 0;


    // Constructeur
    public Hero(int health, int maxHealth, int level, int experience) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.level = level;
        this.experience = experience;
    }


    // Getters
    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getLevel() {
        return this.level;
    }

    public int getExperience() {
        return this.experience;
    }

    
    // Setters

    // Autres méthodes
    private int miseNiveau() {
        if (experience == pointExperience()) {
            this.level = level + 1;
        }

        return this.level;
    }

    private double pointExperience() {
        double exp_requis;
        exp_requis = 50 + (this.level + 1) * 20 * Math.pow(1.1, this.level + 1);

        return exp_requis;
    }
}
