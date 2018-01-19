package arhs.cube.robotshop.core;

import arhs.cube.robotshop.common.exception.ApplicationException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bollenma
 */
public enum RobotType {

    ASTROMECH,
    PROTOCOL,
    SECURITY,
    BATTLE,
    ASSASSIN,
    SCOUT;

    public static RobotType mapRobotType(final String type){
        for (final RobotType enumerate : RobotType.values()) {
            if (StringUtils.equalsIgnoreCase(enumerate.name(), type)) {
                return enumerate;
            }
        }
        throw new ApplicationException("Unforeseen robot type")
                .addContextValue("type", type);
    }
}
