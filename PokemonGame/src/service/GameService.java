package service;

import model.Character;
import model.Player;
import model.Pokemon;
import model.WeatherTypeEnum;

import java.util.*;

public class GameService {

    LoadService loadService;
    PlayerService playerService;

    ArrayList<Character> characterList;
    ArrayList<Pokemon> pokemonList;

    Player player1;
    Player player2;
    boolean starter;

    WeatherTypeEnum weatherType;

    public GameService() {
        loadService = new LoadService();
        playerService = new PlayerService();
    }

    public void attack(Player attacker, Player defender, boolean isPokeSpecialAttack, boolean isCharSpecialAttack) {
        Pokemon attackingPokemon = attacker.getCharacter().getPokemonList().get(0);
        Pokemon defendingPokemon = defender.getCharacter().getPokemonList().get(0);
        int weatherType = new Random().nextInt(1,5);
        for (WeatherTypeEnum w:WeatherTypeEnum.values()
             ) {
            if (w.weatherType == weatherType){
                System.out.println("Hava durumu :" + w);
            }
        }

        boolean specialAttack = false;
        if (isPokeSpecialAttack && isCharSpecialAttack) {
            specialAttack = attackingPokemon.getSpecialPower().getRemainingRights() > 0
                    && attacker.getCharacter().getSpecialPower().getRemainingRights() > 0;
        } else if (isPokeSpecialAttack) {
            specialAttack = attackingPokemon.getSpecialPower().getRemainingRights() > 0;
        } else if (isCharSpecialAttack) {
            specialAttack = attacker.getCharacter().getSpecialPower().getRemainingRights() > 0;
        }

        int charRemainingRights = attacker.getCharacter().getSpecialPower().getRemainingRights();

        int damage = 0;
        if (specialAttack) {
            if (isPokeSpecialAttack && isCharSpecialAttack) {
                damage += attackingPokemon.specialAttack();
                damage += attacker.getCharacter().getSpecialPower().getExtraDamage();
                attacker.getCharacter().getSpecialPower().setRemainingRights(charRemainingRights - 1);
            } else if (isPokeSpecialAttack) {
                damage += attackingPokemon.specialAttack();
            } else {
                damage += attackingPokemon.getDamage();
                damage += attacker.getCharacter().getSpecialPower().getExtraDamage();
                attacker.getCharacter().getSpecialPower().setRemainingRights(charRemainingRights - 1);
            }
        } else {
            if (isPokeSpecialAttack || isCharSpecialAttack) {
                damage = 0;
            } else {
                damage += attackingPokemon.getDamage();
            }
        }
        if (weatherType == attackingPokemon.getWeakness().weatherType){
            damage = damage/2;
            System.out.println("Saldıran pokemonun hasarı hava durumu nedeniyle yarıya düştü");
        }
        defendingPokemon.setHealth(defendingPokemon.getHealth() - damage);
    }

    public boolean healthCheck(Player player){
        if(player.getCharacter().getPokemonList().get(0).getHealth() > 0){

            System.out.println("Oyun devam ediyor.");
            return true;

        } else {


            return false;
        }
    }



    public void createGame(){
        characterList = loadService.loadCharacters();
        pokemonList = loadService.loadPokemons();

        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1 için isim giriniz : ");
        String player1Name = sc.nextLine();
        System.out.println("Player 2 için isim giriniz : ");
        String player2Name = sc.nextLine();
        starter = new Random().nextBoolean();
        if (starter){
            System.out.println("Player 1, lütfen bir karakter seçin : ");
            for (int i = 0; i < characterList.size(); i++) {
                System.out.println((i+1) + " - " + characterList.get(i).getName());
            }
            if (sc.nextLine().equals("1")) {
                player1 = playerService.createPlayer(player1Name,characterList.get(0));
                player2 = playerService.createPlayer(player2Name,characterList.get(1));
            } else if (sc.nextLine().equals("2")) {
                player1 = playerService.createPlayer(player1Name,characterList.get(1));
                player2 = playerService.createPlayer(player2Name,characterList.get(0));
            }
        }else {
            System.out.println("Player 2, lütfen bir karakter seçin : ");
            for (int i = 0; i < characterList.size(); i++) {
                System.out.println((i+1) + " - " + characterList.get(i).getName());
            }
            if (sc.nextLine().equals("1")) {
                player1 = playerService.createPlayer(player1Name,characterList.get(1));
                player2 = playerService.createPlayer(player2Name,characterList.get(0));
            } else if (sc.nextLine().equals("2")) {
                player1 = playerService.createPlayer(player1Name,characterList.get(0));
                player2 = playerService.createPlayer(player2Name,characterList.get(1));
            }
        }
        if (starter) {
            System.out.println("Player 1, lütfen bir pokemon seçin : ");
            for (int i = 0; i < pokemonList.size(); i++) {
                System.out.println((i+1) + " - " + pokemonList.get(i).getName());
            }
            int chosenPokemon = sc.nextInt()-1;
            player1.getCharacter().getPokemonList().add(pokemonList.get(chosenPokemon));
            pokemonList.remove(chosenPokemon);
            System.out.println("Player 2, lütfen bir pokemon seçin : ");
            for (int i = 0; i < pokemonList.size(); i++) {
                System.out.println((i+1) + " - " + pokemonList.get(i).getName());
            }
            chosenPokemon = sc.nextInt()-1;
            player2.getCharacter().getPokemonList().add(pokemonList.get(chosenPokemon));
            pokemonList.remove(chosenPokemon);
        }else {
            System.out.println("Player 2, lütfen bir pokemon seçin : ");
            for (int i = 0; i < pokemonList.size(); i++) {
                System.out.println((i+1) + " - " + pokemonList.get(i).getName());
            }
            int chosenPokemon = sc.nextInt()-1;
            player2.getCharacter().getPokemonList().add(pokemonList.get(chosenPokemon));
            pokemonList.remove(chosenPokemon);
            System.out.println("Player 1, lütfen bir pokemon seçin : ");
            for (int i = 0; i < pokemonList.size(); i++) {
                System.out.println((i+1) + " - " + pokemonList.get(i).getName());
            }
            chosenPokemon = sc.nextInt()-1;
            player1.getCharacter().getPokemonList().add(pokemonList.get(chosenPokemon));
            pokemonList.remove(chosenPokemon);
        }

    }

    public void startFight(){
        int round = 1;
        Scanner sc = new Scanner(System.in);
        while (true){

            if (starter){
                System.out.println(player1.getName() + " için atak sırası");
                System.out.println("Özel güç kullanmak istemiyorsan 0'a, Sadece Pokemonunun özel " +
                        "gücünü kullanmak istiyorsan 1'e, sadece karakterinin özel gücünü kullanmak istiyorsan" +
                        "2'ye, hem karakterin hem de pokemonun özel gücünü kullanamk istiyorsan 3'e bas");

                String attackNumber = sc.nextLine();
                if (attackNumber.equals("0")){
                    attack(player1,player2,false,false);
                } else if (attackNumber.equals("1")) {
                    attack(player1,player2,true,false);
                } else if (attackNumber.equals("2")) {
                    attack(player1,player2,false,true);
                } else if (attackNumber.equals("3")) {
                    attack(player1,player2,true,true);
                }

                System.out.println("Player 1 pokemon canı : " + player1.getCharacter().getPokemonList().get(0).getHealth());
                System.out.println("Player 2 pokemon canı : " + player2.getCharacter().getPokemonList().get(0).getHealth());

                if (!healthCheck(player2) && round == 1){
                    System.out.println("Player 2'nin pokesi öldü ve tekrar canlanarak player 1 e geçti");
                    System.out.println("Player 2'ye en düşük hasarlı pokemon verildi");
                    Pokemon loserPokemon = player2.getCharacter().getPokemonList().remove(0);
                    player1.getCharacter().getPokemonList().add(loserPokemon);

                    Pokemon lowestDamagePokemon = Collections.min(pokemonList, Comparator.comparingInt(Pokemon::getDamage));
                    player2.getCharacter().getPokemonList().add(lowestDamagePokemon);
                    round++;
                    continue;
                } else if (!healthCheck(player2) && round == 2) {
                    System.out.println("Player 2'nin pokesi öldü ve oyun bitti");
                    break;
                }

                System.out.println(player2.getName() + "için atak sırası");
                System.out.println("Özel güç kullanmak istemiyorsan 0'a, Sadece Pokemonunun özel " +
                        "gücünü kullanmak istiyorsan 1'e, sadece karakterinin özel gücünü kullanmak istiyorsan" +
                        "2'ye, hem karakterin hem de pokemonun özel gücünü kullanamk istiyorsan 3'e bas");

                attackNumber = sc.nextLine();
                if (attackNumber.equals("0")){
                    attack(player2,player1,false,false);
                } else if (attackNumber.equals("1")) {
                    attack(player2,player1,true,false);
                } else if (attackNumber.equals("2")) {
                    attack(player2,player1,false,true);
                } else if (attackNumber.equals("3")) {
                    attack(player2,player1,true,true);
                }

                System.out.println("Player 1 pokemon canı : " + player1.getCharacter().getPokemonList().get(0).getHealth());
                System.out.println("Player 2 pokemon canı : " + player2.getCharacter().getPokemonList().get(0).getHealth());

                if (!healthCheck(player1) && round == 1){
                    System.out.println("Player 1'in pokesi öldü ve tekrar canlanarak player 2 ye geçti");
                    System.out.println("Player 1'e en güçsüz pokemon verildi");
                    Pokemon loserPokemon = player1.getCharacter().getPokemonList().remove(0);
                    player2.getCharacter().getPokemonList().add(loserPokemon);
                    Pokemon lowestDamagePokemon = Collections.min(pokemonList, Comparator.comparingInt(Pokemon::getDamage));
                    player1.getCharacter().getPokemonList().add(lowestDamagePokemon);

                    round++;
                    continue;
                } else if (!healthCheck(player1) && round == 2) {
                    System.out.println("Player 1'in pokesi öldü ve oyun bitti");
                    break;
                }
            }

            else {
                System.out.println(player2.getName() + "için atak sırası");
                System.out.println("Özel güç kullanmak istemiyorsan 0'a, Sadece Pokemonunun özel " +
                        "gücünü kullanmak istiyorsan 1'e, sadece karakterinin özel gücünü kullanmak istiyorsan" +
                        "2'ye, hem karakterin hem de pokemonun özel gücünü kullanamk istiyorsan 3'e bas");

                String attackNumber = sc.nextLine();
                if (attackNumber.equals("0")){
                    attack(player2,player1,false,false);
                } else if (attackNumber.equals("1")) {
                    attack(player2,player1,true,false);
                } else if (attackNumber.equals("2")) {
                    attack(player2,player1,false,true);
                } else if (attackNumber.equals("3")) {
                    attack(player2,player1,true,true);
                }
                System.out.println("Player 1 pokemon canı : " + player1.getCharacter().getPokemonList().get(0).getHealth());
                System.out.println("Player 2 pokemon canı : " + player2.getCharacter().getPokemonList().get(0).getHealth());

                if (!healthCheck(player1) && round == 1){
                    System.out.println("Player 1'in pokesi öldü ve tekrar canlanarak player 1 e geçti");
                    System.out.println("Player 1'e en güçsüz pokemon verildi");
                    Pokemon loserPokemon = player1.getCharacter().getPokemonList().remove(0);
                    player2.getCharacter().getPokemonList().add(loserPokemon);
                    Pokemon lowestDamagePokemon = Collections.min(pokemonList, Comparator.comparingInt(Pokemon::getDamage));
                    player1.getCharacter().getPokemonList().add(lowestDamagePokemon);
                    round++;
                    continue;
                } else if (!healthCheck(player1) && round == 2) {
                    System.out.println("Player 1'in pokesi öldü ve oyun bitti");
                    break;
                }

                System.out.println(player1.getName() + "için atak sırası");
                System.out.println("Özel güç kullanmak istemiyorsan 0'a, Sadece Pokemonunun özel " +
                        "gücünü kullanmak istiyorsan 1'e, sadece karakterinin özel gücünü kullanmak istiyorsan" +
                        "2'ye, hem karakterin hem de pokemonun özel gücünü kullanamk istiyorsan 3'e bas");

                attackNumber = sc.nextLine();
                if (attackNumber.equals("0")){
                    attack(player1,player2,false,false);
                } else if (attackNumber.equals("1")) {
                    attack(player1,player2,true,false);
                } else if (attackNumber.equals("2")) {
                    attack(player1,player2,false,true);
                } else if (attackNumber.equals("3")) {
                    attack(player1,player2,true,true);
                }
                System.out.println("Player 1 pokemon canı : " + player1.getCharacter().getPokemonList().get(0).getHealth());
                System.out.println("Player 2 pokemon canı : " + player2.getCharacter().getPokemonList().get(0).getHealth());

                if (!healthCheck(player2) && round == 1){
                    System.out.println("Player 2'nin pokesi öldü ve tekrar canlanarak player 1 e geçti");
                    System.out.println("Player 2'ye en güçsüz pokemon verildi");
                    Pokemon loserPokemon = player2.getCharacter().getPokemonList().remove(0);
                    player1.getCharacter().getPokemonList().add(loserPokemon);
                    Pokemon lowestDamagePokemon = Collections.min(pokemonList, Comparator.comparingInt(Pokemon::getDamage));
                    player2.getCharacter().getPokemonList().add(lowestDamagePokemon);
                    round++;

                } else if (!healthCheck(player2) && round == 2) {
                    System.out.println("Player 2'nin pokesi öldü ve oyun bitti");
                    break;
                }
            }
        }
    }


}
