package org.forje.spikes;

import com.google.gson.Gson;

/**
 * Created by Brian on 4/15/15.
 */
public class GsonSpike {

    public static void main(String[] args) {

        final Weapon weapon = new Weapon();
        weapon.name = "Sniper Rifle, .338";
        weapon.acc = "6+3";
        weapon.dmg = "9d+1 pi";
        weapon.rng = "1500/5500";
        weapon.rof = 1;
        weapon.shots = "4+1(3)";
        weapon.cost = 5600;
        weapon.str = "11B†";
        weapon.wgt = "17.5/0.8";

        Gson gson = new Gson();
        String json = gson.toJson(weapon);

        System.out.println("json = " + json);


        final Weapon fromJson = gson.fromJson(json, Weapon.class);

        System.out.println(fromJson.name);
        System.out.println(fromJson.rng);
        System.out.println(fromJson.dmg);
        System.out.println(fromJson.shots);

    }

    private static class Weapon {
        private int TL; // tech level
        private String name;
        private String dmg; // damage
        private String acc; // accuracy
        private String rng; // range
        private int rof; // rate of fire
        private String shots;
        private int cost;
        private String wgt; // weight
        private String str; // strength
    }


}
