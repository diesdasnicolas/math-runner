import greenfoot.*;

public class Scroller {
    public World world;
    public GreenfootImage scrollImage;
    public boolean limited;
    public int scrolledX, scrolledY;
    public int wide, high;

    public Scroller(World viewWorld, GreenfootImage image)
    {
        world = viewWorld;
        scrollImage = image;
        if (image != null)
        {
            wide = image.getWidth();
            high = image.getHeight();
        }
        scroll(0, 0); // sets initial background image
    }

    public Scroller(World viewWorld, GreenfootImage image, int wide, int high)
    {
        this.wide = wide;
        this.high = high;
        limited = false;
        world = viewWorld;

        scrollImage = new GreenfootImage(wide * world.getCellSize(), high * world.getCellSize());

        for (int i = 0; i < wide * world.getCellSize(); i += image.getWidth())
            for (int j = 0; j < high * world.getCellSize(); j += image.getHeight())
                scrollImage.drawImage(image, i, j);

        scroll(0, 0);
    }

    /**
     * Performs scrolling on 'world' by the given distances along the horizontal and vertical
     *
     * @param dsx The requested distance to shift everything horizontally
     * @param dsy The requested distance to shift everything vertically
     */
    public void scroll(int dsx, int dsy)
    {
        if (limited) {
            // Calculate limits of scrolling
            int maxX = wide - world.getWidth();
            int maxY = high - world.getHeight();

            // Apply limits to distances to scroll
            if (scrolledX + dsx < 0)
                dsx = -scrolledX;

            if (scrolledX + dsx >= maxX)
                dsx = maxX-scrolledX;

            if (scrolledY + dsy < 0)
                dsy = -scrolledY;

            if (scrolledY + dsy >= maxY)
                dsy = maxY-scrolledY;

            // Update scroll positions
            scrolledX += dsx;
            scrolledY += dsy;

            // Scroll background image
            if (scrollImage != null)
                world.getBackground().drawImage(scrollImage, -scrolledX * world.getCellSize(),
                        -scrolledY * world.getCellSize());
        }

        else {
            // Update scroll positions
            scrolledX += dsx;

            // Scroll background image
            if (scrollImage != null) {
                int imageX = scrolledX * world.getCellSize();
                int imageY = scrolledY * world.getCellSize();

                // Get near-zero starting positions for drawing 'scrollImage'
                imageX = imageX % wide;
                imageY = imageY % high;

                // Adjust negative values as needed
                if (imageX < 0)
                    imageX += wide;

                if (imageY < 0)
                    imageY += high;

                // Create image of appropriate size and tile fill 'scrollImage' onto it
                GreenfootImage hold = new GreenfootImage(scrollImage);

                hold.drawImage(scrollImage, -imageX, -imageY);

                if (imageX > 0)
                    hold.drawImage(scrollImage, wide-imageX, -imageY);

                if (imageY > 0)
                    hold.drawImage(scrollImage, -imageX, high-imageY);

                if (imageX > 0 && imageY > 0)
                    hold.drawImage(scrollImage, wide-imageX, high-imageY);

                // set image to background of 'world'
                world.setBackground(hold);
            }
        }
        // adjust position of all actors (that can move with 'setLocation')
        for (Object obj : world.getObjects(null)) {
            Actor actor = (Actor) obj;
            actor.setLocation(actor.getX() - dsx, actor.getY());
        }
    }

    /**
     * Fetching method for the current total scrolled distance horizontally
     *
     * @return The current total offset of horizontal scrolling
     */
    public int getScrolledX()
    {
        return scrolledX;
    }

    /**
     * Fetching method for the current total scrolled distance vertically
     *
     * @return The current total offset of vertical scrolling
     */
    public int getScrolledY()
    {
        return scrolledY;
    }
}
