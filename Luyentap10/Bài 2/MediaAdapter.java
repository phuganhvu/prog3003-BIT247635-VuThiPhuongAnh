public class MediaAdapter implements MediaPlayer {

    VlcPlayer vlcPlayer;

    public MediaAdapter() {
        vlcPlayer = new VlcPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {

        if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.playVlc(fileName);
        } else {
            System.out.println("Unsupported format");
        }
    }
}