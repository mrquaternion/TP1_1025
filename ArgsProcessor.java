public class ArgsProcessor {
    public static void process(String[] args) {
        String[] phrase = makePhrase(args[0]);
        Hero hero;
        String nom = phrase[0];
        
        switch (nom.charAt(0)) {
            case 'A':
                hero = new HeroAttaque(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                break;
            case 'D':
                hero = new HeroDefense(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                break;
            default:
                hero = new HeroEquilibre(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                break;
        }

        String phraseFinale = "In his quest, " + nom;
        for (int i = 3; i < phrase.length; i++) { // la 3ième position dans le tableau est la première phrase conçernant l'action commis par le héro
            doAction(phrase[i], hero);
            if (hero.getHealth() <= 0) { break; }
        }
        if (hero.getHealth() <= 0) {
            phraseFinale += " died after beating " + hero.numberOfEnemiesDefeated + " enemies and attaining level " + hero.getLevel() + "!";
        } else {
            phraseFinale += " beat " + hero.numberOfEnemiesDefeated + " enemies, attained level " + hero.getLevel() + " and survived with " + hero.health + " HP!";
        }
        System.out.println(hero.getMaxHealth());
        System.out.println(phraseFinale);
    }


        //TODO : Handle the phrase and output the result
    

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
                
                for (int i = 1; i <= numberOfEnemies; i++) { // Combat jusqu'à temps que tous les ennemis soient battus 
                    
                    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                    System.out.println("%%%%%%%%%%%%%%%%%% Combat " + (hero.numberOfEnemiesDefeated + 1) + " %%%%%%%%%%%%%%%%%%");
                    System.out.println("      Hero's health: " + hero.getHealth()); // TEST
                    System.out.println("      Enemy's health: " + enemy.getHealth()); // TEST
                    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

                    if (hero.fighting(enemy)) { // Combat

                        hero.numberOfEnemiesDefeated++; // On itère

                        // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                        System.out.println("The hero defeated " + hero.numberOfEnemiesDefeated + " enemy."); // TEST
                        System.out.println("Hero's health after combat " + hero.numberOfEnemiesDefeated + ": " + hero.getHealth()); // TEST
                        System.out.println("Next enemy's health (+10): " + enemy.getHealth() + ", next enemy's attack power (+5): " + enemy.getAttackPower()); // TEST
                        System.out.println("--------------------------------"); // TEST
                        // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

                        hero.levelUp(); // level up apres tous les combats
                    } else { // Si le héro est mort alors on arrête tout

                        // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                        System.out.println("The hero is dead.");
                        // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

                        return false;
                    }
                }   
                
                break;
            case "rested":
                //TODO : Handle the resting
                hero.resting();

                break;
            case "healed":
                //TODO : Handle the healing
                hero.healing(Integer.parseInt(phrase[1]));

                break;
            case "trained":
                //TODO : Handle the training
                hero.training(Integer.parseInt(phrase[3]));

                break;
        }
        return true;
    }
}
