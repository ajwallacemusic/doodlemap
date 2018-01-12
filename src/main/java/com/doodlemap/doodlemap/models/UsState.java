package com.doodlemap.doodlemap.models;

public enum UsState {

    ALABAMA("Alabama","AL","UsState-AL"),
    ALASKA("Alaska","AK","UsState-AK"),
    ARIZONA("Arizona","AZ","UsState-AZ"),
    ARKANSAS("Arkansas","AR","UsState-AR"),
    CALIFORNIA("California","CA","UsState-CA"),
    COLORADO("Colorado","CO","UsState-CO"),
    CONNECTICUT("Connecticut","CT","UsState-CT"),
    DELAWARE("Delaware","DE","UsState-DE"),
    DISTRICT_OF_COLUMBIA("District of Columbia","DC","UsState-DC"),
    FLORIDA("Florida","FL","UsState-FL"),
    GEORGIA("Georgia","GA","UsState-GA"),
    HAWAII("Hawaii","HI","UsState-HI"),
    IDAHO("Idaho","ID","UsState-ID"),
    ILLINOIS("Illinois","IL","UsState-IL"),
    INDIANA("Indiana","IN","UsState-IN"),
    IOWA("Iowa","IA","UsState-IA"),
    KANSAS("Kansas","KS","UsState-KS"),
    KENTUCKY("Kentucky","KY","UsState-KY"),
    LOUISIANA("Louisiana","LA","UsState-LA"),
    MAINE("Maine","ME","UsState-ME"),
    MARYLAND("Maryland","MD","UsState-MD"),
    MASSACHUSETTS("Massachusetts","MA","UsState-MA"),
    MICHIGAN("Michigan","MI","UsState-MI"),
    MINNESOTA("Minnesota","MN","UsState-MN"),
    MISSISSIPPI("Mississippi","MS","UsState-MS"),
    MISSOURI("Missouri","MO","UsState-MO"),
    MONTANA("Montana","MT","UsState-MT"),
    NEBRASKA("Nebraska","NE","UsState-NE"),
    NEVADA("Nevada","NV","UsState-NV"),
    NEW_HAMPSHIRE("New Hampshire","NH","UsState-NH"),
    NEW_JERSEY("New Jersey","NJ","UsState-NJ"),
    NEW_MEXICO("New Mexico","NM","UsState-NM"),
    NEW_YORK("New York","NY","UsState-NY"),
    NORTH_CAROLINA("North Carolina","NC","UsState-NC"),
    NORTH_DAKOTA("North Dakota","ND","UsState-ND"),
    OHIO("Ohio","OH","UsState-OH"),
    OKLAHOMA("Oklahoma","OK","UsState-OK"),
    OREGON("Oregon","OR","UsState-OR"),
    PENNSYLVANIA("Pennsylvania","PA","UsState-PA"),
    RHODE_ISLAND("Rhode Island","RI","UsState-RI"),
    SOUTH_CAROLINA("South Carolina","SC","UsState-SC"),
    SOUTH_DAKOTA("South Dakota","SD","UsState-SD"),
    TENNESSEE("Tennessee","TN","UsState-TN"),
    TEXAS("Texas","TX","UsState-TX"),
    UTAH("Utah","UT","UsState-UT"),
    VERMONT("Vermont","VT","UsState-VT"),
    VIRGINIA("Virginia","VA","UsState-VA"),
    WASHINGTON("Washington","WA","UsState-WA"),
    WEST_VIRGINIA("West Virginia","WV","UsState-WV"),
    WISCONSIN("Wisconsin","WI","UsState-WI"),
    WYOMING("Wyoming","WY","UsState-WY");


    String unabbreviated;
    String ANSIabbreviation;
    String ISOabbreviation;

    UsState(String unabbreviated, String ANSIabbreviation, String ISOabbreviation) {
        this.unabbreviated = unabbreviated;
        this.ANSIabbreviation = ANSIabbreviation;
        this.ISOabbreviation = ISOabbreviation;
    }

    /**
     * The full, unabbreviated name of this state.
     */
    public String getUnabbreviated() {
        return this.unabbreviated;
    }

    /**
     * The ANSI abbreviated name of this state, e.g. "NY", or "WY".
     */
    public String getANSIAbbreviation() {
        return this.ANSIabbreviation;
    }

    /**
     * The ISO abbreviated name of this state, e.g. "UsState-NY", or "UsState-WY".
     */
    public String getISOAbbreviation() {
        return this.ISOabbreviation;
    }

    /**
     * Parse string input to enum. Accepts unabbreviated and abbreviated forms.
     * Case insensitive.
     * @param input String to parse
     * @return The parsed UsState state, or null on failure.
     */
    public static UsState parse(String input) {
        if (null == input) {
            return null;
        }
        input = input.trim();
        for (UsState state : values()) {
            if (state.unabbreviated.equalsIgnoreCase(input)    ||
                    state.ANSIabbreviation.equalsIgnoreCase(input) ||
                    state.ISOabbreviation.equalsIgnoreCase(input)) {
                return state;
            }
        }
        return null;
    }
}
