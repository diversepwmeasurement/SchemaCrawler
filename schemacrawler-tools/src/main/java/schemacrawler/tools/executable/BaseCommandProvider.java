/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2020, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/
package schemacrawler.tools.executable;


import static java.util.Objects.requireNonNull;
import static us.fatehi.utility.Utility.isBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.tools.executable.commandline.PluginCommand;
import schemacrawler.tools.options.Config;
import schemacrawler.tools.options.OutputOptions;

public abstract class BaseCommandProvider
  implements CommandProvider
{
  private final Collection<CommandDescription> supportedCommands;

  public BaseCommandProvider(final Collection<CommandDescription> supportedCommands)
  {
    this.supportedCommands =
      requireNonNull(supportedCommands, "No supported commands provided");
  }

  public BaseCommandProvider(final CommandDescription supportedCommand)
  {
    this(Arrays.asList(requireNonNull(supportedCommand,
                                      "No command provided")));
  }

  @Override
  public final Collection<CommandDescription> getSupportedCommands()
  {
    return new ArrayList<>(supportedCommands);
  }

  @Override
  public boolean supportsSchemaCrawlerCommand(final String command,
                                              final SchemaCrawlerOptions schemaCrawlerOptions,
                                              final Config additionalConfiguration,
                                              final OutputOptions outputOptions)
  {
    return supportsCommand(command);
  }

  @Override
  public PluginCommand getCommandLineCommand()
  {
    return PluginCommand.empty();
  }

  protected boolean supportsOutputFormat(final String command,
                                         final OutputOptions outputOptions,
                                         final Predicate<String> outputFormatValuePredicate)
  {
    requireNonNull(outputFormatValuePredicate,
                   "No output format value predicate provided");
    if (outputOptions == null)
    {
      return false;
    }
    final String format = outputOptions.getOutputFormatValue();
    if (isBlank(format))
    {
      return false;
    }
    return outputFormatValuePredicate.test(format);
  }

  protected final boolean supportsCommand(final String command)
  {
    if (isBlank(command))
    {
      return false;
    }
    for (final CommandDescription commandDescription : supportedCommands)
    {
      if (commandDescription == null)
      {
        continue;
      }
      if (command.equalsIgnoreCase(commandDescription.getName()))
      {
        return true;
      }
    }
    return false;
  }

}
