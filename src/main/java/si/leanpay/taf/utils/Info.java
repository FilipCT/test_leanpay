package si.leanpay.taf.utils;

public class Info {
    private static String scenarioName;
    private static String featureName;
    private static int exampleNumber;
    private static int scenarioNumber;
    private Info ourInstance = new Info();

    public Info getInstance() {
        return ourInstance;
    }

    private Info() {
    }

    public static int getScenarioNumber() {
        return scenarioNumber;
    }

    public static void resetExampleNumber() {
        exampleNumber = 1;
    }

    public static void incExampleNumber() {
        exampleNumber = exampleNumber + 1;
    }

    public static void incScenarioNumber() {
        scenarioNumber = scenarioNumber + 1;
    }

    public static String getScenarioName() {
        return scenarioName;
    }

    public static void setScenarioName(String scenarioName) {
        Info.scenarioName = scenarioName;
    }

    public static String getFeatureName() {
        return featureName;
    }

    public static void setFeatureName(String featureName) {
        Info.featureName = featureName;
    }

    public static int getExampleNumber() {
        return exampleNumber;
    }

    public static void setExampleNumber(int exampleNumber) {
        Info.exampleNumber = exampleNumber;
    }
}
