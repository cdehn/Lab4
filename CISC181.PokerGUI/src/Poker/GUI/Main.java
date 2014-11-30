package Poker.GUI;

import java.awt.EventQueue;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import pokerAction.Action;
import pokerBase.Card;
import pokerBase.Hand;
import pokerBase.Player;
import pokerBase.Rule;
import pokerBase.Table;
import pokerEnums.eGame;
import pokerPlay.Client;

public class Main extends JFrame implements Client {
	
	/** The table. */
	private Table tbl;

	private Map<String, Player> players;

	/** The GridBagConstraints. */
	private GridBagConstraints gc;

	/** The board panel. */
	private BoardPanel boardPanel;

	/** The control panel. */
	private ControlPanel controlPanel;

	/** The player panels. */
	private Map<String, PlayerPanel> playerPanels;

	private JButton btnEnd;
	private JButton btnStart;
	private Action selectedAction;
	private PlayGame play;
	private Rule rule;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Main window = new Main();
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		tbl = new Table();
		Rule rle = new Rule(eGame.FiveStudTwoJoker);
		PlayGame pGame = new PlayGame(eGame.FiveStudTwoJoker);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(UIConstants.TABLE_COLOR);
		setLayout(new GridBagLayout());

		gc = new GridBagConstraints();

		// Control Panel
		controlPanel = new ControlPanel();
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				play.run();
			}
		});
		controlPanel.add(btnStart);

		JButton btnEnd = new JButton("End");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		controlPanel.add(btnEnd);

		
		// menu bar to choose game
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu game = new JMenu("Game");
		game.addSeparator();
		
		
		JRadioButtonMenuItem rdbtnCardJoker = new JRadioButtonMenuItem("5 Card Joker");
		rdbtnCardJoker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rule = new Rule(eGame.FiveStudTwoJoker);
				play = new PlayGame(eGame.FiveStudTwoJoker);
				
				
				play.run();
				play.winner();
				}	
		});
		game.add(rdbtnCardJoker);

		
		JRadioButtonMenuItem rdbtnCardWild = new JRadioButtonMenuItem("5 Card Wild");
		rdbtnCardWild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rule = new Rule(eGame.DeucesWild);
				play = new PlayGame(eGame.DeucesWild);
				for (int i = 0; i < rule.GetNumberOfCards(); i++) {
				
				}
				play.run();
				play.winner();
			}
		});
		game.add(rdbtnCardWild);

		
		JRadioButtonMenuItem rdbtnCardDraw5 = new JRadioButtonMenuItem("5 Card Draw");
		rdbtnCardDraw5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rule = new Rule(eGame.FiveStud);
				play = new PlayGame(eGame.FiveStud);
				
				play.run();
				play.winner();				
			}
		});
		game.add(rdbtnCardDraw5);

		
		JRadioButtonMenuItem rdbtnCardDraw7 = new JRadioButtonMenuItem("7 Card Draw");
		rdbtnCardDraw7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rule = new Rule(eGame.SevenDraw);
				play = new PlayGame(eGame.SevenDraw);
				
				
				play.run();
				play.winner();
			}
		});
		game.add(rdbtnCardDraw7);

		
		JRadioButtonMenuItem rdbtnTexas = new JRadioButtonMenuItem("Texas Hold‘em");
		rdbtnTexas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rule = new Rule(eGame.TexasHoldEm);
				play = new PlayGame(eGame.TexasHoldEm);
				
				
				play.run();
				play.winner();
			}
		});
		game.add(rdbtnTexas);

		
		JRadioButtonMenuItem rdbtnOmaha = new JRadioButtonMenuItem("Omaha");
		rdbtnOmaha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rule = new Rule(eGame.Omaha);
				play = new PlayGame(eGame.Omaha);
				
				
				play.run();
				play.winner();
			}
		});
		game.add(rdbtnOmaha);

		
		ButtonGroup menuGroup = new ButtonGroup();
		menuGroup.add(rdbtnCardWild);
		menuGroup.add(rdbtnCardJoker);
		menuGroup.add(rdbtnCardDraw5);
		menuGroup.add(rdbtnCardDraw7);
		menuGroup.add(rdbtnTexas);
		menuGroup.add(rdbtnOmaha);
		menuBar.add(game);
		menuBar.setVisible(true);

		
		boardPanel = new BoardPanel(controlPanel, rle);
		addComponent(boardPanel, 1, 1, 1, 1);

		players = new LinkedHashMap<String, Player>();
		Player p1 = new Player("Bert", this);
		players.put(p1.GetPlayerID().toString(), p1);

		Player p2 = new Player("Joe", this);
		players.put(p2.GetPlayerID().toString(), p2);

		Player p3 = new Player("Jim", this);
		players.put(p3.GetPlayerID().toString(), p3);

		Player p4 = new Player("Bob", this);
		players.put(p4.GetPlayerID().toString(), p4);

		for (Player player : players.values()) {
			pGame.AddPlayer(player);
		}

		playerPanels = new HashMap<String, PlayerPanel>();

		int i = 0;
		for (Player player : players.values()) {
			PlayerPanel panel = new PlayerPanel(tbl, rle, player);
			playerPanels.put(player.GetPlayerName(), panel);
			switch (i++) {
			case 0:
				// North position.
				addComponent(panel, 1, 0, 1, 1);
				break;
			case 1:
				// East position.
				addComponent(panel, 2, 1, 1, 1);
				break;
			case 2:
				// South position.
				addComponent(panel, 1, 2, 1, 1);
				break;
			case 3:
				// West position.
				addComponent(panel, 0, 1, 1, 1);
				break;
			default:
				// Do nothing.
			}
		}

		// Show the frame.
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		pGame.run();

	}

	private void addComponent(Component component, int x, int y, int width, int height) {
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		gc.weightx = 0.0;
		gc.weighty = 0.0;
		getContentPane().add(component, gc);
	}

	@Override
	public void messageReceived(String message) {
		boardPanel.setMessage(message);
		boardPanel.waitForUserInput();

	}

	@Override
	public void joinedTable(List<Player> players) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handStarted(Player dealer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actorRotated(Player actor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerUpdated(Player player) {
		System.out.println("PlayerUpdated in Main");
		PlayerPanel playerPanel = playerPanels.get(player.GetPlayerName());
		if (playerPanel != null) {
			playerPanel.update(player);
		}

	}

	@Override
	public void boardUpdated(List<Card> cards) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerActed(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public Action act(Set<Action> allowedActions) {
		// TODO Auto-generated method stub
		return null;
	}

}
