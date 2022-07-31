package ui;

import core.Position;
import core.Size;

public class VerticalContainer extends UIContainer {

    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildHeight = 0;
        int widestChildWidth = 0;

        for(UIComponent uiComponent : children) {
            combinedChildHeight += uiComponent.getSize().getHeight() + uiComponent.getMargin().getVertical();

            if(uiComponent.getSize().getWidth() > widestChildWidth) {
                widestChildWidth = uiComponent.getSize().getWidth();
            }
        }

        return new Size(widestChildWidth, combinedChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentY = padding.getTop();
        int currentX = padding.getLeft();

        for(UIComponent uiComponent : children) {
            currentY += uiComponent.getMargin().getTop();
            if(centerChildren){
                currentX = getSize().getWidth()/2 - uiComponent.getSize().getWidth() / 2;
            }

            uiComponent.setRelativePosition(new Position(currentX, currentY));
            uiComponent.setAbsolutePosition(new Position(currentX + absolutePosition.getIntX(), currentY + absolutePosition.getIntY()));
            currentY += uiComponent.getSize().getHeight();
            currentY += uiComponent.getMargin().getBottom();
        }
    }
}
