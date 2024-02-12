public class ArgsProcessor {
    public static void process(String[] args) {
        String[] phrase = makePhrase(args[0]);
        Hero hero;
        String nom = phrase[0];
        System.out.println(phrase[1]);
        
        switch (nom.charAt(0)) {
            case 'A':
                hero = new HeroAttaque(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                hero = new HeroAttaque(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                break;
            case 'D':
                hero = new HeroDefense(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                break;
            default:
                hero = new HeroEquilibre(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                break;
        }

        String phraseFinale = "In his quest, ";
        int i = 3; // la 3ième position dans le tableau est la première phrase conçernant l'action commis par le héro

        while (i < phrase.length) {
            doAction(phrase[i], hero); 
            i++;
        }
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
                int numberOfEnemiesDefeated = 0;

                while (numberOfEnemiesDefeated < Integer.parseInt(phrase[1])) { // Combat jusqu'à temps que tous les ennemis soient battus   
                    if (hero.fighting(enemy).equals("alive")) { // Combat
                        numberOfEnemiesDefeated++; // On itère
                        System.out.println("The hero defeated " + numberOfEnemiesDefeated + " enemy."); // TEST
                        System.out.println("Hero's health after combat " + numberOfEnemiesDefeated + ": " + hero.getHealth()); // TEST
                        System.out.println("Next enemy's health (+10): " + enemy.getHealth() + ", next enemy's attack power (+5): " + enemy.getAttackPower()); // TEST
                        System.out.println("--------------------------------"); // TEST
                    } else { // Si le héro est mort alors on arrête tout
                        System.out.println("The hero is dead.");
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
                hero.healing();

                break;
            case "trained":
                //TODO : Handle the training
                hero.training();

                break;
        }
        return true;
    }

    private static void choixType(String[] phrase){
        
    }
}
