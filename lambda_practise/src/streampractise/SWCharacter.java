package streampractise;

public class SWCharacter {

    private String name;
    private Double height;
    private Double mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private Integer birthYear;
    private String gender;

    public SWCharacter(String[] fileContent) {
        name = fileContent[0];
        try {
            height = Double.parseDouble(fileContent[1]);
        } catch (Exception e) {
        }
        try {
            mass = Double.parseDouble(fileContent[2]);
        } catch (Exception e) {
        }
        hairColor = fileContent[3];
        skinColor = fileContent[4];
        eyeColor = fileContent[5];
        try {
            birthYear = Integer.parseInt(fileContent[6].replace("BBY", ""));
        } catch (Exception e) {
        }
        gender = fileContent[7];
    }

    public String getName() {
        return name;
    }

    public Double getHeight() {
        return height;
    }

    public Double getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "exercise12.SWCharacter{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", mass=" + mass +
                ", hairColor='" + hairColor + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", birthYear=" + birthYear +
                ", gender='" + gender + '\'' +
                '}';
    }
}
