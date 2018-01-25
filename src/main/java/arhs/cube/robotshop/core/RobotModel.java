package arhs.cube.robotshop.core;

import java.util.ArrayList;
import java.util.Collection;

import arhs.cube.robotshop.common.exception.ApplicationException;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bollenma
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RobotModel {

    DOMESTIC("DOMESTIC", "Domestic robot"),
    MEDICAL("MEDICAL", "Medical droid"),
    PROTOCOL("PROTOCOL", "Protocol droid"),
    TECHNICAL("TECHNICAL", "Technical robot");

    private final String value;
    private final String label;

    RobotModel(final String value, final String label) {
        this.value = value;
        this.label = label;
    }

    public static RobotModel mapRobotModel(final String model) {
        for (final RobotModel enumerate : RobotModel.values()) {
            if (StringUtils.equalsIgnoreCase(enumerate.name(), model)) {
                return enumerate;
            }
        }
        throw new ApplicationException("Unforeseen robot model")
                .addContextValue("model", model);
    }

    public static Collection<RobotModel> getAllModels() {
        final Collection<RobotModel> ret = new ArrayList<>();

        CollectionUtils.addAll(ret, RobotModel.values());
        return ret;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
