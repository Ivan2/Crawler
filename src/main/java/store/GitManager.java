package store;


import logic.GitSettings;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class GitManager implements IGitManager{

	private GitSettings gitSettings;
	private Repository repository;
	private Git git;

	public GitManager(GitSettings gitSettings){
		this.gitSettings = gitSettings;
		try {
			repository = new RepositoryBuilder().setGitDir
					(new File(gitSettings.getGitDirectory()+"/.git")).build();
			git = new Git(repository);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void sendFile() {
		addFiles();
		commit();
		push();
	}

	private void addFiles(){
		try {
			git.add().addFilepattern(".").call();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private void commit(){
		try {
			git.commit().setMessage("data").call();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private void push(){
		try {
			PushCommand push = git.push();
			UsernamePasswordCredentialsProvider user =
					new UsernamePasswordCredentialsProvider
							(gitSettings.getLogin(), gitSettings.getPassword());
			push.setCredentialsProvider(user);
			push.call();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
