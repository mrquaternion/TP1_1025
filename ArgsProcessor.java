public class ArgsProcessor {
    public static void process(String[] args) {
        String[] phrase = makePhrase(args[0]);
        Hero hero;
        String nom = phrase[0];
        
        switch (nom.charAt(0)) {
            case 'A':
                hero = new HeroAttaque(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // Création de l'instance HeroAttaque
                break;
            case 'D':
                hero = new HeroDefense(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // Création de l'instance HeroDefense
                break;
            default:
                hero = new HeroEquilibre(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // Création de l'instance HeroEquilibre
                break;
        }

        String phraseFinale = "In his quest, " + nom;
        for (int i = 3; i < phrase.length; i++) { // La 3ième position dans le tableau est la première phrase conçernant l'action commis par le héro
            doAction(phrase[i], hero);
            if (hero.getHealth() <= 0) { break; }
        }

        // On crée les phrases pour les 2 cas possibles
        if (hero.getHealth() <= 0) {
            phraseFinale += " died after beating " + hero.numberOfEnemiesDefeated + " enemies and attaining level " + hero.getLevel() + "!";
        } else {
            phraseFinale += " beat " + hero.numberOfEnemiesDefeated + " enemies, attained level " + hero.getLevel() + " and survived with " + hero.health + " HP!";
        }

        System.out.println(phraseFinale); // On output la phrase finale
    }  

    private static String[] makePhrase(String args) {
        return args.trim().split(",");
    }

    // méthode qui prend la partie de la phrase qui décrit l'action et le héros, puis effectue l'action correspondante
    // retourne true si le joueur survit à l'action, false sinon
    private static boolean doAction(String action, Hero hero) {
        // ici, on transforme le String action en un tableau de String, en séparant les mots par des espaces
        String[] phrase = action.trim().split(" ");
        // le type d'action est déterminé par le premier mot de la phrase
        switch (phrase[0]) {
            case "fought":
                Enemy enemy = new Enemy(); // On crée l'ennemi ici et non dans la méthode fighting() de Hero autrement une nouvelle instance sera toujours créée
                
                for (int i = 0; i < hero.numberOfEnemiesDefeated; i++) { // À chaque phrase, on doit garder les stats de l'instance Enemy
                    enemy.statsUpdate();
                }
                
                int numberOfEnemies = Integer.parseInt(phrase[1]);
                if (numberOfEnemies > 0) {
                    for (int i = 1; i <= numberOfEnemies; i++) { // Combat jusqu'à temps que tous les ennemis soient battus 
                        if (hero.fighting(enemy)) { // Combat
                            hero.numberOfEnemiesDefeated++; // On itère
                            hero.levelUp(); // Level up apres tous les combats
                        } else { return false; } // Si le héro est mort alors on arrête tout
                    } 
                } else { throw new IllegalArgumentException("Il n'y a aucun ennemi à combattre."); }
                break;

            case "rested":
                hero.resting();
                break;

            case "healed":
                hero.healing(Integer.parseInt(phrase[1]));
                break;

            case "trained":
                hero.training(Integer.parseInt(phrase[3]));
                break;
        }
        return true;
    }
}
