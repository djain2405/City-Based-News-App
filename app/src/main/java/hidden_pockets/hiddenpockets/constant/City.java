package hidden_pockets.hiddenpockets.constant;

/**
 * Created by Divya Jain on 8/1/2017.
 */

public enum City {


    AHMEDABAD("Ahmedabad"),
    ALIGARH("Aligarh"),
    BANGALORE("Bangalore"),
    BHOPAL("Bhopal"),
    CHANDIGARH("Chandigarh"),
    CHENNAI("Chennai"),
    COCHIN("Cochin"),
    JAIPUR("Jaipur"),
    KOHIMA("Kohima"),
    KOLKATA("Kolkata"),
    LUCKNOW("Lucknow"),
    MUMBAI("Mumbai"),
    NEWDELHI("New Delhi"),
    PUNE("Pune"),
    RANCHI("Ranchi");

    String city;

    private City(String str) {
        this.city = str;
    }

    @Override
    public String toString() {
        return city;
    }

    public static City fromString(String str) {
        if (str != null) {
            for (City ctf : City.values()) {
                if (str.equalsIgnoreCase(ctf.city)) {
                    return ctf;
                }
            }
        }
        return null;
    }

    public static City[] cities()
    {
        City[] ct = City.values();
        return ct;
    }
    public static String[] names() {
        City[] ct = City.values();

        String[] names = new String[ct.length];

        for (int i = 0; i < ct.length; i++) {
            names[i] = ct[i].city;
        }
        return names;
    }


}
