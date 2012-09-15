# JacksonPass

Dynamically changing password security application for Fall 2012 PennApps competition


### Block Data Format:

This is the data format that our clientside password creation page will expect the available
block data to appear in.

	<categories>
		<category>
			<blocks>
				<block>
					<traits>
						<trait name="short_name">3 Ltr. Day</trait>
						<trait name="full_name">3 Letter Day of the Week</trait>
						<trait name="pattern">%a%</trait>
					</traits>
				</block>
				<block>
					<traits>
						<trait name="short_name">Full Day</trait>
						<trait name="full_name">Full Day of the Week</trait>
						<trait name="pattern">%b%</trait>
					</traits>
				</block>
				<block>
					<traits>
						<trait name="short_name">1 Ltr. Day</trait>
						<trait name="full_name">1 Letter Day of the Week</trait>
						<trait name="pattern">%c%</trait>
					</traits>
				</block>
			</blocks>
			<traits>
				<trait name="name">Weekday</trait>
				<trait name="cat_color">#f00</trait>
				<trait name="block_color">#0f0</trait>
			</traits>
		</category>
		<category>
			<blocks>
				<block>
					<traits>
						<trait name="short_name">Full Month</trait>
						<trait name="full_name">Full Month Name</trait>
						<trait name="pattern">%d%</trait>
					</traits>
				</block>
			</blocks>
			<traits>
				<trait name="name">Month</trait>
				<trait name="cat_color">#00f</trait>
				<trait name="block_color">#f00</trait>
			</traits>
		</category>
	</categories>



