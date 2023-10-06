package br.com.leo.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.leo.cm.modelo.Campo;
import br.com.leo.cm.modelo.CampoEvento;
import br.com.leo.cm.modelo.CampoObservador;

public class BotaoCampo extends JButton implements CampoObservador, MouseListener{

	private final Color BG_PADRAO = new Color(184,184,184);
	private final Color BG_MARCAR = new Color(8,179,247);
	private final Color BG_EXPLODIR = new Color(189,66,68);
	private final Color TEXTO_VERDE = new Color(0,100,0);
	private Campo campo;
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADRAO);
		
		addMouseListener(this);
		campo.registarObservador(this);
		
	}

	@Override
	public void eventoOcorreu(Campo c, CampoEvento evento) {
		switch(evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		
		case MARCAR:
			aplicarEstiloMarcar();
			break;
			
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
		
		default:
			aplicarEstiloPadrao();
		}
	}

	private void aplicarEstiloPadrao() {
		setBackground(BG_PADRAO);
		setText("");
		setBorder(BorderFactory.createBevelBorder(0));
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLODIR);
		setText("X");
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCAR);
		setText("?");
		
	}

	private void aplicarEstiloAbrir() {
		
		setBorder(BorderFactory.createLineBorder(Color.gray));

		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}
		
		setBackground(BG_PADRAO);
		
		switch (campo.minasNaVizinhanca()){
		case 1: 
			setForeground(TEXTO_VERDE);
			break;
			
		case 2:
			setForeground(Color.blue);
			break;
			
		case 3: 
			setForeground(Color.YELLOW);
			break;
			
		case 4:
		case 5:
		case 6:
			setForeground(Color.red);
			break;
		default:
			setForeground(Color.PINK);
			break;
		}
		
		String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";
		setText(valor);
	}

	
	// INTERFACE DOS EVENTOS DO MOUSE 
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()== 1) {
			System.out.println("Botao esquerdo");
			campo.abrir();
		}
		else if(e.getButton()==3) {
			campo.alternarMarcacao();
		}
		else {
			System.out.println("outro botao");
		}
	}
		
	
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	
}