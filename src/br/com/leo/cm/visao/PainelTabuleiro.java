package br.com.leo.cm.visao;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.leo.cm.modelo.Tabuleiro;

public class PainelTabuleiro extends JPanel{

	public PainelTabuleiro(Tabuleiro tabuleiro) {

		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
		
		tabuleiro.paraCada(c -> add(new BotaoCampo(c)));
		
		tabuleiro.registrarObservador(e -> {
			
			SwingUtilities.invokeLater(() -> {
				
				if(e.booleanValue()) {
					JOptionPane.showMessageDialog(null, "Ganhou");
				}
				else {
					JOptionPane.showMessageDialog(null, "Perdeu");
					}
				tabuleiro.reiniciar();
			});
			
		});
	}
}
