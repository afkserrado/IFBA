package ifba.inf011.p2.s26_1.q3.proxy;

import ifba.inf011.p2.s26_1.domain.track.AudioTrack;

// Proxy
public class AudioTrackProtegidaProxy extends AudioTrack {

    private AudioTrack inner;
    private Credencial credencial;
    private Autenticador autenticador;

    public AudioTrackProtegidaProxy(AudioTrack inner, Credencial credencial) {
        super(inner.getStreamName(), inner.getDurationInSeconds());
        this.inner = inner;
        this.credencial = credencial;
        this.autenticador = new Autenticador();
    }

    private void checkAccess() {
        if (!this.autenticador.autenticar(this.credencial)) {
            throw new AccessDeniedException("Acesso negado ao conteúdo de áudio licenciado.");
        }
    }

    @Override
    public byte[] getContent() {
        this.checkAccess();
        return this.inner.getContent();
    }

    @Override
    public String render() {
        return this.inner.render();
    }

    @Override
    public String getStreamName() {
        return this.inner.getStreamName();
    }

    @Override
    public void setStreamName(String streamName) {
        this.inner.setStreamName(streamName);
    }

    @Override
    public int getDurationInSeconds() {
        return this.inner.getDurationInSeconds();
    }

    @Override
    public void setDurationInSeconds(int durationInSeconds) {
        this.inner.setDurationInSeconds(durationInSeconds);
    }

    @Override
    public void setContent(byte[] content) {
        this.inner.setContent(content);
    }
}