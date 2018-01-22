package arhs.cube.robotshop.core;

import arhs.cube.robotshop.common.exception.ApplicationException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bollenma
 */
public enum RobotModel {

    ASTROMECH,
    PROTOCOL,
    MEDICAL,
    BATTLE,
    ASSASSIN,
    SCOUT;

    public static RobotModel mapRobotModel(final String model){
        for (final RobotModel enumerate : RobotModel.values()) {
            if (StringUtils.equalsIgnoreCase(enumerate.name(), model)) {
                return enumerate;
            }
        }
        throw new ApplicationException("Unforeseen robot model")
                .addContextValue("model", model);
    }
}
