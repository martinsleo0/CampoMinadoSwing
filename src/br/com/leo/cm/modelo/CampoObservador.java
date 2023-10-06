package br.com.leo.cm.modelo;
@FunctionalInterface
public interface CampoObservador {

	public void eventoOcorreu(Campo c, CampoEvento evento);
}
